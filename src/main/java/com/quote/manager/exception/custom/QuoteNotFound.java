package com.quote.manager.exception.custom;

public class QuoteNotFound extends RuntimeException{
    public QuoteNotFound(String message) {
        super(message);
    }
}
