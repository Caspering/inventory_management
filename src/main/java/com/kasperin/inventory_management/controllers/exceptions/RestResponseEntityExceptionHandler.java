package com.kasperin.inventory_management.controllers.exceptions;

import com.kasperin.inventory_management.validator_services.ValidationErrorResponse;
import com.kasperin.inventory_management.validator_services.Violation;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Date;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Object> handleEntityNotFound(ResourceNotFoundException ex,
                                                       WebRequest request){

        ExceptionResponse exceptionResponse = new ExceptionResponse(HttpStatus.NOT_FOUND,
                                              new Date(),
                                              ex.getMessage(),
                                              request.getDescription(false),
                                              ex.getLocalizedMessage());

        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    ValidationErrorResponse onConstraintValidationException(ConstraintViolationException e) {
        ValidationErrorResponse error = new ValidationErrorResponse();
        for (ConstraintViolation violation : e.getConstraintViolations()) {
            error.getViolations().add(
                    new Violation(violation.getPropertyPath().toString(), violation.getMessage()));
        }
        return error;
    }


    public ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex,
                                                     final HttpHeaders headers,
                                                     final HttpStatus status,
                                                     final WebRequest request){

        logger.info(ex.getClass().getName());

        ExceptionResponse apiError = new ExceptionResponse(HttpStatus.BAD_REQUEST,
                new Date(),
                "Unable tp convert '"+ ex.getValue()+ "' to an id number. Use id number",
                request.getDescription(false),
                ex.toString());

        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());

    }


    // 405

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(final HttpRequestMethodNotSupportedException ex,
                                                                         final HttpHeaders headers,
                                                                         final HttpStatus status,
                                                                         final WebRequest request) {
        logger.info(ex.getClass().getName());
        //log.error("Method Not Allowed" + ex);
        //
        final StringBuilder builder = new StringBuilder();
        builder.append(ex.getMethod());
        builder.append(" method is not supported for this request. Supported methods are ");
        ex.getSupportedHttpMethods().forEach(t -> builder.append(t).append(" "));

        final ExceptionResponse apiError = new ExceptionResponse(HttpStatus.METHOD_NOT_ALLOWED,
                                                                new Date(),
                                                                ex.getLocalizedMessage(),
                                                                ex.getMethod(),
                                                                builder.toString());

        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }


    // 415

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(final HttpMediaTypeNotSupportedException ex,
                                                                     final HttpHeaders headers,
                                                                     final HttpStatus status,
                                                                     final WebRequest request) {
        logger.info(ex.getClass().getName());
        //
        final StringBuilder builder = new StringBuilder();
        builder.append(ex.getContentType());
        builder.append(" media type is not supported. Supported media types are ");
        ex.getSupportedMediaTypes().forEach(t -> builder.append(t).append(" "));

        final ExceptionResponse apiError = new ExceptionResponse(HttpStatus.UNSUPPORTED_MEDIA_TYPE,
                                                                ex.getLocalizedMessage(),
                                                                builder.substring(0, builder.length() - 2));
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }


    // 500

    @ExceptionHandler({ Exception.class })
    public ResponseEntity<Object> handleAll(final Exception ex,
                                            final WebRequest request) {
        logger.info(ex.getClass().getName());
        logger.error("error", ex);
        //
        final ExceptionResponse apiError = new ExceptionResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getLocalizedMessage(), "error occurred");
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }



    @Override
    public ResponseEntity<Object> handleHttpMessageNotReadable(final HttpMessageNotReadableException ex,
                                                  final HttpHeaders headers,
                                                  final HttpStatus status,
                                                  final WebRequest request) {

        logger.info(ex.getClass().getName());

        ExceptionResponse apiError = new ExceptionResponse(HttpStatus.BAD_REQUEST,
                new Date(),
                "Invalid Input: "+ ex.getCause(),
                request.getDescription(true),
                ex.toString());

        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());

    }
}
