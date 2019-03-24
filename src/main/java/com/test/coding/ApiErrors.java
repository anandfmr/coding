package com.test.coding;

import com.test.coding.exceptions.DuplicateApplicationException;
import com.test.coding.exceptions.ExceptionResponse;
import com.test.coding.exceptions.OfferNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ApiErrors {


    @ResponseBody
    @ExceptionHandler(OfferNotFoundException.class)
    public ExceptionResponse handleOfferNotFoundException(OfferNotFoundException e) {
        ExceptionResponse exceptionResponse = new ExceptionResponse();
        exceptionResponse.setErrorMessage(e.getMessage());
        exceptionResponse.setRequestedURI("/application");
        return exceptionResponse;
    }

    @ResponseBody
    @ExceptionHandler(DuplicateApplicationException.class)
    public ExceptionResponse handleDuplicateApplicationException(DuplicateApplicationException e) {
        ExceptionResponse exceptionResponse = new ExceptionResponse();
        exceptionResponse.setErrorMessage(e.getMessage());
        exceptionResponse.setRequestedURI("/application");
        return exceptionResponse;
    }
}
