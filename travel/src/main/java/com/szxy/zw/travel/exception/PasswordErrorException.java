package com.szxy.zw.travel.exception;

/**
 * 密码错误异常
 */
public class PasswordErrorException extends Exception {
    public PasswordErrorException(){

    }
    public PasswordErrorException(String errorMsg){
        super(errorMsg);
    }
}
