package com.college.yi.ecsite.exception;

//認証エラー用の例外
public class AuthenticationException extends RuntimeException{
    public AuthenticationException(String message) {
        super(message);
    }
    
}
