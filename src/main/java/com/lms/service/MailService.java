package com.lms.service;

import com.lms.exception.MailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {
    @Autowired
    private JavaMailSender javaMailSender;

    public void sendPasswordToEmail(String email, String password) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("moaazsuliman1@gmail.com");
            message.setTo(email);
            message.setText("Your Password From Moaaz And Unkown React Developer "
                    + password + " \n I Will Fuck You If You Forget Password Again...");
            message.setSubject("Health Care");
            javaMailSender.send(message);

        } catch (Exception mailException) {
            throw new MailException("Error In Your Internet Connection To Sent Otp For This Email");
        }

    }

    public void sayHello(String email) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("moaazsuliman1@gmail.com");
            message.setTo(email);
            message.setText("Welcome In Our Online Health Care , We Hope You Are Doing Well");
            message.setSubject("Moaaz And Unkown React Developer");
            javaMailSender.send(message);
        } catch (Exception mailException) {
            throw new MailException("Error In Your Internet Connection To Sent Otp For This Email");
        }

    }

    public void sendMessage(String email, String content) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("moaazsuliman1@gmail.com");
            message.setTo(email);
            message.setText(content);
            message.setSubject("Moaaz And Unkown React Developer");
            javaMailSender.send(message);
        } catch (Exception mailException) {
            throw new MailException("Error In Your Internet Connection To Sent Otp For This Email");
        }
    }

    public void sendOtp(String email, String otp) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("moaazsuliman1@gmail.com");
            message.setTo(email);
            message.setText("Your Otp To Verify Your Email Account In Our Health Care Is " + otp);
            message.setSubject("Moaaz And Unkown React Developer");
            javaMailSender.send(message);
        } catch (Exception mailException) {
            throw new MailException("Error In Your Internet Connection To Sent Otp For This Email");
        }
    }

    private void justSendThis(String content) {

    }


}
