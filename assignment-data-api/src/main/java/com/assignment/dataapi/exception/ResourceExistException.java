package com.assignment.dataapi.exception;

public class ResourceExistException extends RuntimeException{

    public ResourceExistException(String errorMessage) {
        super(errorMessage);
    }
}
