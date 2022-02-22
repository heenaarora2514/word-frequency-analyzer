package com.java.project.wordfrequencyanalyzer.model;

import lombok.Data;

@Data
public class ErrorObject {
    private String errorCode;
    private String errorMessage;

    public ErrorObject(String errorCode, String errorMessage) {
        super();
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
}
