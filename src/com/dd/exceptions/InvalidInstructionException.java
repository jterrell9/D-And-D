package com.dd.exceptions;

public class InvalidInstructionException extends RuntimeException{
    public InvalidInstructionException(String message){
        super(message);
    }
}
