package com.sus.exception;

/**
 * Created by Administrator on 2017/3/18.
 */
public class ServiceException extends RuntimeException {

    public ServiceException() {}

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(Throwable th,String message) {
        super(message,th);
    }
}
