package com.java.project.wordfrequencyanalyzer.exception;

import lombok.Getter;

@Getter
public class DataNotFoundException extends RuntimeException {

    private final String exceptionMessage;

    public DataNotFoundException(String exceptionMessage) {
        super(exceptionMessage);
        this.exceptionMessage = exceptionMessage;
    }
}
