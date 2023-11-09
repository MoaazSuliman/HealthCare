package com.lms.exception;

import javax.naming.AuthenticationException;

public class OtpVerificationException extends AuthenticationException {

    public OtpVerificationException(String message){
        super(message);
    }
}
