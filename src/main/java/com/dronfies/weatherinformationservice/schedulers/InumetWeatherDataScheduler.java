package com.dronfies.weatherinformationservice.schedulers;

import com.dronfies.weatherinformationservice.daos.WeatherDataDAO;
import com.dronfies.weatherinformationservice.daos.WeatherStationDAO;
import com.dronfies.weatherinformationservice.entities.LatLngAlt;
import com.dronfies.weatherinformationservice.entities.Unit;
import com.dronfies.weatherinformationservice.entities.WeatherData;
import com.dronfies.weatherinformationservice.entities.WeatherStation;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.*;

@Component
public class InumetWeatherDataScheduler {

    @Data
    @AllArgsConstructor
    private class EstacionJson{
        private int id;
        private String estacion;
        private String nombreEstacion;
    }

    @Data
    @AllArgsConstructor
    private class VariableJson{
        private int id;
        private String variable;
        private String nombre;
        private String unidad;
    }

    @Data
    @AllArgsConstructor
    private class ObservacionJson {
        private List<Double> datos;
    }

    @Autowired
    private WeatherStationDAO weatherStationDAO;

    @Autowired
    private WeatherDataDAO weatherDataDAO;

    // it will be executed every minute%10=0 (0, 10, 20, 30, 40 and 50)
    @Scheduled(cron = "0 */10 * * * *")
    public void scheduler(){
        try{
            // get the source code of the web page
            String url = "https://www.inumet.gub.uy/tiempo/estaciones-meteorologicas-automaticas";
            Document document = Jsoup.connect(url).get();
            // get the script with the data
            Elements scripts = document.getElementsByTag("script");
            String script = null;
            try{
                script = scripts.stream().filter(element -> element.html().startsWith("var estadoActual")).toList().get(0).html();
            }catch (Exception ex){}
            if(script == null) return;
            // extract from the script the json with the wheather data
            int indexStart = script.indexOf("{\"estaciones\"");
            int indexEnd = script.indexOf("var estaciones");
            String jsonWithWeatherData = script.substring(indexStart, indexEnd).trim();
            if(jsonWithWeatherData.endsWith(";")) jsonWithWeatherData = jsonWithWeatherData.substring(0, jsonWithWeatherData.length()-1);
            // parse json
            Map<String, Object> mapJson = new ObjectMapper().readValue(jsonWithWeatherData, Map.class);

            // get inumet weather stations
            List<WeatherStation> weatherStations = weatherStationDAO.getWeatherStationsByNetworkId(1);

            // get estaciones json
            List<EstacionJson> estaciones = getEstaciones(mapJson);

            // get variables
            List<VariableJson> variables = getVariables(mapJson);

            // get observaciones
            List<ObservacionJson> observaciones = getObservaciones(mapJson);

            // get date
            Date date = null;
            try{
                String strDate = ((List<String>)mapJson.get("fechas")).get(0);
                date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSXXX").parse(strDate.replaceAll("T", " "));
            }catch (Exception ex){}
            if(date == null) return;

            // parse weather data
            List<WeatherData> listWeatherData = getWeatherData(weatherStations, estaciones, variables, observaciones, date);

            // save data into the database
            weatherDataDAO.updateWeatherData(listWeatherData);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    private List<EstacionJson> getEstaciones(Map<String, Object> mapJson){
        List<Map<String, Object>> mapEstacionesJson = ((List)mapJson.get("estaciones"));
        List<EstacionJson> result = new ArrayList<>();
        for(Map<String, Object> estacionJson : mapEstacionesJson){
            result.add(new EstacionJson(
                (int)estacionJson.get("id"),
                estacionJson.get("Estacion") + "",
                estacionJson.get("NombreEstacion") + ""
            ));
        }
        return result;
    }

    private List<VariableJson> getVariables(Map<String, Object> mapJson){
        List<Map<String, Object>> mapVariablesJson = ((List)mapJson.get("variables"));
        List<VariableJson> result = new ArrayList<>();
        for(Map<String, Object> variableJson : mapVariablesJson){
            result.add(new VariableJson(
                (int)variableJson.get("idInt"),
                variableJson.get("variable") + "",
                variableJson.get("nombre") + "",
                variableJson.get("unidad") + ""
            ));
        }
        return result;
    }

    private List<ObservacionJson> getObservaciones(Map<String, Object> mapJson){
        List<Map<String, Object>> mapObservacionesJson = ((List)mapJson.get("observaciones"));
        List<ObservacionJson> result = new ArrayList<>();
        for(Map<String, Object> observacionJson : mapObservacionesJson){
            List<Double> datos = ((List<List>)observacionJson.get("datos")).stream().map(dato -> {
                try{
                    return Double.parseDouble(dato.get(0).toString());
                }catch (Exception ex){
                    return null;
                }
            }).toList();
            result.add(new ObservacionJson(datos));
        }
        return result;
    }

    private long getWeatherStationId(List<WeatherStation> weatherStations, EstacionJson estacionJson){
        for(WeatherStation weatherStation : weatherStations){
            if(weatherStation.getName().equalsIgnoreCase(estacionJson.getNombreEstacion())){
                return weatherStation.getId();
            }
        }
        return -1;
    }

    private Map<String, Integer> getMapIndexPorVariable(List<VariableJson> variables){
        Map<String, Integer> result = new HashMap<>();
        for(int i = 0; i < variables.size(); i++){
            result.put(variables.get(i).getVariable(), i);
        }
        return result;
    }

    private Map<String, String> getMapUnidadPorVariable(List<VariableJson> variables){
        Map<String, String> result = new HashMap<>();
        for(int i = 0; i < variables.size(); i++){
            result.put(variables.get(i).getVariable(), variables.get(i).getUnidad());
        }
        return result;
    }

    private Map<String, Long> getMapWeatherStationIdPorEstacion(List<WeatherStation> weatherStations, List<EstacionJson> estaciones){
        Map<String, Long> result = new HashMap<>();
        for(EstacionJson estacionJson : estaciones){
            for(WeatherStation weatherStation : weatherStations){
                if(estacionJson.getNombreEstacion().equalsIgnoreCase(weatherStation.getName())){
                    result.put(estacionJson.getEstacion(), weatherStation.getId());
                }
            }
        }
        return result;
    }

    private List<WeatherData> getWeatherData(List<WeatherStation> weatherStations, List<EstacionJson> estaciones, List<VariableJson> variables, List<ObservacionJson> observaciones, Date datetime){
        Map<String, Integer> mapIndexPorVariable = getMapIndexPorVariable(variables);
        Map<String, String> mapUnidadPorVariable = getMapUnidadPorVariable(variables);
        Map<String, Long> mapWeatherStationIdPorEstacion = getMapWeatherStationIdPorEstacion(weatherStations, estaciones);
        List<WeatherData> result = new ArrayList<>();
        for(int i = 0; i < estaciones.size(); i++){
            EstacionJson estacionJson = estaciones.get(i);
            if(!mapWeatherStationIdPorEstacion.containsKey(estacionJson.getEstacion())) continue;
            result.add(new WeatherData(
                new WeatherStation(mapWeatherStationIdPorEstacion.get(estacionJson.getEstacion()), null, null, new LatLngAlt(0, 0, null), null),
                datetime,
                observaciones.get(mapIndexPorVariable.get("DirViento")).getDatos().get(i),
                observaciones.get(mapIndexPorVariable.get("IntViento")).getDatos().get(i),
                getSpeedUnit(mapUnidadPorVariable.get("IntViento")),
                observaciones.get(mapIndexPorVariable.get("TempAire")).getDatos().get(i),
                getTemperatureUnit(mapUnidadPorVariable.get("TempAire")),
                observaciones.get(mapIndexPorVariable.get("TempPtoRocio")).getDatos().get(i),
                getTemperatureUnit(mapUnidadPorVariable.get("TempPtoRocio")),
                observaciones.get(mapIndexPorVariable.get("HumRelativa")).getDatos().get(i),
                observaciones.get(mapIndexPorVariable.get("precipHoraria")).getDatos().get(i),
                getLengthUnit(mapUnidadPorVariable.get("precipHoraria")),
                observaciones.get(mapIndexPorVariable.get("PresAtmMar")).getDatos().get(i),
                getPressureUnit(mapUnidadPorVariable.get("PresAtmMar")),
                observaciones.get(mapIndexPorVariable.get("RadiaciónSolarGlobal")).getDatos().get(i),
                getRadiationUnit(mapUnidadPorVariable.get("RadiaciónSolarGlobal"))
            ));
        }
        return result;
    }

    private Unit.Speed getSpeedUnit(String unit){
        if(unit.equals("nudos")) return Unit.Speed.KNOT;
        return null;
    }

    private Unit.Temperature getTemperatureUnit(String unit){
        if(unit.equals("ºC")) return Unit.Temperature.CELSIUS;
        return null;
    }

    private Unit.Length getLengthUnit(String unit){
        if(unit.equals("mm")) return Unit.Length.mm;
        return null;
    }

    private Unit.Pressure getPressureUnit(String unit){
        if(unit.equals("hPa")) return Unit.Pressure.hPa;
        return null;
    }

    private Unit.Radiation getRadiationUnit(String unit){
        if(unit.equals("mW/m²")) return Unit.Radiation.mW_PER_M2;
        return null;
    }
}
