package com.gildata.byinterserver.exception;

/**
 * Created by LiChao on 2018/11/8
 */

public class InvalidRequestException extends RuntimeException{

    public InvalidRequestException(String message) {
        super(message);
    }
}

