package com.vmware.backend.validator;

public class UserDoesnExistException extends Exception{
    public UserDoesnExistException() {
    }

    public UserDoesnExistException(String message) {
        super(message);
    }

    public UserDoesnExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserDoesnExistException(Throwable cause) {
        super(cause);
    }

    public UserDoesnExistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
