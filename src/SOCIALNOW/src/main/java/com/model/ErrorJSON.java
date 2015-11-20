package com.model;

/**
 * Created by Erdem on 11/20/2015.
 */
public class ErrorJSON {

    String message;
    int code;

    @Override
    public String toString() {
        return "ErrorJSON{" +
                "message='" + message + '\'' +
                ", code=" + code +
                '}';
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
