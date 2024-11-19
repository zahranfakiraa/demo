package com.techmarket.demo.exception;

public class AuthenticationFailException extends IllegalArgumentException{
    public AuthenticationFailException(String msg){
        super(msg);
    }
}
