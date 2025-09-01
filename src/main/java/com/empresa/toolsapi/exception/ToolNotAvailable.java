package com.empresa.toolsapi.exception;

public class ToolNotAvailable extends RuntimeException{
    public ToolNotAvailable(String message){
        super(message);
    }
}
