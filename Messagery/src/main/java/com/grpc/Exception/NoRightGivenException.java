package com.grpc.Exception;

public class NoRightGivenException extends Exception{
    String message;
    public NoRightGivenException(String message){
        super(message);     
        this.message = message;
    }    
    @Override
    public String getMessage(){
        return this.message;
    }
}
