# Weather Information Service

## Description

Weather Information Service is a REST API that supplies (as the name says) wheather information. Is one of the modules of the CIELUM UTM, but it is totally independent from the other modules of the solution, so it can be use in any kind of project that needs weather information.

## Installation and Usage

1 Clone the repository:

    git clone https://github.com/dronfies/weather-information-service.git

2 Create an empty database

3 Complete the application.yaml configuration file (indicating the database connection string)

4 Build the project:

    ./gradlew build

5 The jar file will be created in build/libs. Execute it:

    java -jar weather-information-service-0.0.1-SNAPSHOT
