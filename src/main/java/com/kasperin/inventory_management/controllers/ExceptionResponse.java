package com.kasperin.inventory_management.controllers;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Data
//@AllArgsConstructor
public class ExceptionResponse {
    private Date timestamp;
    private String message;
    private String details;
    private HttpStatus status;
    private List<String> errors;


    public ExceptionResponse(final HttpStatus status, final String message, final String error) {
        super();
        this.status = status;
        this.message = message;
        errors = Arrays.asList(error);
    }


    public ExceptionResponse(HttpStatus status, Date timestamp, String message, String details, String error) {
        super();
        this.timestamp = timestamp;
        this.status = status;
        this.message = message;
        this.details = details;
        errors = Arrays.asList(error);
    }



}
