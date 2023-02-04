package com.assignment.assignmentcsvprocessor.exception;

public class CSVFormatException extends RuntimeException{
    public CSVFormatException(String errorMessage) {
        super(errorMessage);
    }

    public CSVFormatException(String errorMessage, Throwable cause) {
        super(errorMessage, cause);
    }
}
