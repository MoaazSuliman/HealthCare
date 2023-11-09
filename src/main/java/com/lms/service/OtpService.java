
package com.lms.service;

import com.lms.model.Teacher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@Slf4j
public class OtpService {

    @Autowired
    private MailService mailService;

    @Autowired
    private TeacherService teacherService;


    public String createOtpAndSendItToEmail(String email) {
        log.info("Creating Otp");
        Random random = new Random();
        int otp = 1000 + random.nextInt(9000); // Generate a random number between 1000 and 9999
        log.info("This is Otp {0} ", otp);
        String otpString = String.valueOf(otp);
//        mailService.sendOtp(email, otpString);
        log.info("Created Otp");
        return otpString;
    }

    public boolean checkOtp(String email, String otp) {
        return checkOtpForTeacher(email, otp);
    }

    private boolean checkOtpForTeacher(String email, String otp) {
        Teacher teacher = teacherService.getByEmail(email);
        if (teacher != null) {
            if (teacher.getAuthentication().getOtp().equals(otp)) {
                teacher.getAuthentication().setVerify(true);
                teacherService.justUpdate(teacher);
                return true;
            } else {
                teacherService.deleteById(teacher.getId());
                return false;
            }
        }

        return false;
    }


}
