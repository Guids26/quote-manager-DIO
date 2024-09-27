package com.quote.manager.exception.handler;

import com.quote.manager.dto.ErrorResponse;
import com.quote.manager.exception.custom.QuoteNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(QuoteNotFound.class)
    public ResponseEntity<ErrorResponse> handleQuoteNotFound( RuntimeException exception,  WebRequest request){
        ErrorResponse errorResponse = new ErrorResponse(404, exception.getMessage(), request.getDescription(false).replace("uri=", ""));

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, WebRequest request){
        ErrorResponse errorResponse = new ErrorResponse(400, null, request.getDescription(false).replace("uri=", ""));
        ex.getBindingResult().getFieldErrors().forEach(error -> errorResponse.getFieldErrors().add(error.getDefaultMessage()));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }




}
