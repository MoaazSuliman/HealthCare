package com.lms.service;

import com.lms.model.Teacher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class LoginService {

    @Autowired
    private TeacherService teacherService;


    @Autowired
    private MailService mailService;

    public ResponseEntity<?> login(String email, String password) {
        Teacher teacher = teacherService.login(email, password);
        if (teacher != null) {
            if (teacher.getAuthentication().isVerify()) {
                return new ResponseEntity<>(teacher, HttpStatus.ACCEPTED);
            } else
                return new ResponseEntity<>("This Doctor Not Verify His OTP", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>("Error In Email Or Password", HttpStatus.BAD_REQUEST);
    }


    public ResponseEntity<?> forgetPassword(String email) {
        Teacher teacher = teacherService.getByEmail(email);
        if (teacher != null) {
            mailService.sendPasswordToEmail(email, teacher.getAuthentication().getPassword());
            return new ResponseEntity<>("Password Sent Successfully", HttpStatus.ACCEPTED);
        }

        return new ResponseEntity<>("Error In Email", HttpStatus.BAD_REQUEST);
    }
}


