package com.lookie.toy1_back.tome.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class UserNotFoundException  extends RuntimeException{
    public UserNotFoundException(Long id){
        super("를 찾을 수 없습니다."+id);
    }
}