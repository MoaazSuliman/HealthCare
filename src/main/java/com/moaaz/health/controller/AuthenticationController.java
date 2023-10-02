package com.moaaz.health.controller;

import com.moaaz.health.service.LoginService;
import com.moaaz.health.service.OtpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/moaaz/api/health/auth")
@RestController
public class AuthenticationController {

    @Autowired
    private LoginService loginService;
    @Autowired
    private OtpService otpService;


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String email, @RequestParam String password) {
        return loginService.login(email, password);
    }

    @PostMapping("/forgetPassword")
    public ResponseEntity<?> forgetPassword(@RequestParam String email) {
        return loginService.forgetPassword(email);
    }

    @PostMapping("/verifyOtp")
    public ResponseEntity<?> verifyOtp(@RequestParam String email, @RequestParam String otp) {
        return new ResponseEntity<>(otpService.checkOtp(email, otp), HttpStatus.ACCEPTED);
    }


}
