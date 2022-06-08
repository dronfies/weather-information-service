package com.dronfies.weatherinformationservice.daos.exceptions;

public class NoDataException extends RuntimeException{

    public NoDataException(){
        super();
    }

    public NoDataException(String message){
        super(message);
    }

    public NoDataException(Throwable exc){
        super(exc);
    }

    public NoDataException(String message, Throwable exc){
        super(message, exc);
    }
}
