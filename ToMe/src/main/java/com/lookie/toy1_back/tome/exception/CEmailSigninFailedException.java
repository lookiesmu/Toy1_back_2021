package com.lookie.toy1_back.tome.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

public class CEmailSigninFailedException extends RuntimeException{
    public CEmailSigninFailedException(String msg, Throwable t) {
        super(msg, t);
    }

    public CEmailSigninFailedException(String msg) {
        super(msg);
    }

    public CEmailSigninFailedException() {
        super();
    }

}



