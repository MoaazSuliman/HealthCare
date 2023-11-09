package com.lms.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MailException extends RuntimeException {

    public MailException(String message) {

        super("Your Internet Isn't Good ==> So Spring Boot Application Doesn't Sent Otp For Gmail. , Try Register Again To Add Your Account");
        log.info(message);
    }
}
