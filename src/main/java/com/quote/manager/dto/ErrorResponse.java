package com.quote.manager.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ErrorResponse {
    private int statusCode;
    private String message;
    private long timestamp;
    private String path;
    private List<String> fieldErrors;

    public ErrorResponse(int statusCode, String message, String path) {
        this.statusCode = statusCode;
        this.message = message;
        this.timestamp = System.currentTimeMillis();
        this.path = path;
        this.fieldErrors = new ArrayList<String>();
    }

    public List<String> getFieldErrors() {
        return fieldErrors;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
