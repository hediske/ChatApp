package com.grpc.Exception;

public class NonExistantException extends Exception{
    String message;
    public NonExistantException(String message){
        super(message);     
        this.message = message;
    }   
    @Override
    public String getMessage(){
        return this.message;
    } 
    
}
