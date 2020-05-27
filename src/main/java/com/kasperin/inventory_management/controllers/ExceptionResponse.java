package com.kasperin.inventory_management.controllers;

import lombok.AllArgsConstructor;
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
    //private String message;
    private List<String> errors;

    public ExceptionResponse() {
        super();
    }

    public ExceptionResponse(final HttpStatus status, final String message, final List<String> errors) {
        super();
        this.status = status;
        this.message = message;
        this.errors = errors;
    }

    public ExceptionResponse(final HttpStatus status, final String message, final String error) {
        super();
        this.status = status;
        this.message = message;
        errors = Arrays.asList(error);
    }

    public ExceptionResponse(Date timestamp, String message, String details) {
        super();
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
    }

    public ExceptionResponse(HttpStatus httpStatus, String error, Date timestamp, String message, String details) {
        super();
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
        errors = Arrays.asList(error);
    }

    public ExceptionResponse(final HttpStatus status, final String message, Date timestamp){
        super();
        this.status = status;
        this.message = message;
        this.timestamp = timestamp;

    }

    public ExceptionResponse(HttpStatus status, Date timestamp, String message, String details, String error) {
        super();
        this.timestamp = timestamp;
        this.status = status;
        this.message = message;
        this.details = details;
        errors = Arrays.asList(error);
    }

    public ExceptionResponse(HttpStatus status, Date timestamp, String message, String error) {
        super();
        this.timestamp = timestamp;
        this.status = status;
        this.message = message;
        errors = Arrays.asList(error);
    }





    public void setStatus(final HttpStatus status) {
        this.status = status;
    }


    public void setError(final String error) {
        errors = Arrays.asList(error);
    }



}
