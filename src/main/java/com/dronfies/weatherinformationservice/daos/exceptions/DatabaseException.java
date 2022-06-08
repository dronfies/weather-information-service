package com.dronfies.weatherinformationservice.daos.exceptions;

public class DatabaseException extends RuntimeException{

    public DatabaseException(String message){
        super(message);
    }

    public DatabaseException(Throwable exc){
        super(exc);
    }

    public DatabaseException(String message, Throwable exc){
        super(message, exc);
    }
}
