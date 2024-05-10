package com.grpc.Exception;

public class AlreadyExistsException extends Exception{
    String message;
    public AlreadyExistsException(String message){
        super(message);     
        this.message = message;
    }    
    @Override
    public String getMessage(){
        return this.message;
    }
}
