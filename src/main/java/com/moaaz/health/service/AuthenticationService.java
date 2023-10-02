package com.moaaz.health.service;

import com.moaaz.health.model.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    @Autowired
    private OtpService otpService;


    public Authentication createAuthentication(String email, String password) {
        return Authentication
                .builder()
                .email(email)
                .password(password)
                .isVerify(false)
                .otp(otpService.createOtpAndSendItToEmail(email))
                .build();
    }




}
