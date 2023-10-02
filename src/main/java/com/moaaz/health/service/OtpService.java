package com.moaaz.health.service;

import com.moaaz.health.model.Doctor;
import com.moaaz.health.model.Patient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.print.Doc;
import java.util.Random;
import java.util.UUID;

@Service
@Slf4j
public class OtpService {

    @Autowired
    private MailService mailService;

    @Autowired
    private DoctorService doctorService;
    @Autowired
    private PatientService patientService;

    public String createOtpAndSendItToEmail(String email) {
        log.info("Creating Otp");
        Random random = new Random();
        int otp = 1000 + random.nextInt(9000); // Generate a random number between 1000 and 9999
        log.info("This is Otp {0} ", otp);
        String otpString = String.valueOf(otp);
        mailService.sendOtp(email, otpString);
        log.info("Created Otp");
        return otpString;
    }

    public boolean checkOtp(String email, String otp) {
        return checkOtpForDoctor(email, otp) || checkOtpForPatient(email, otp);
    }

    private boolean checkOtpForDoctor(String email, String otp) {
        Doctor doctor = doctorService.getByEmail(email);
        if (doctor != null) {
            if (doctor.getAuthentication().getOtp().equals(otp)) {
                doctor.getAuthentication().setVerify(true);
                doctorService.justUpdate(doctor);
                return true;
            } else {
                doctorService.deleteById(doctor.getId());
                return false;
            }
        }

        return false;
    }

    private boolean checkOtpForPatient(String email, String otp) {
        Patient patient = patientService.getByEmail(email);
        if (patient != null) {
            if (patient.getAuthentication().getOtp().equals(otp)) {
                patient.getAuthentication().setVerify(true);
                patientService.justUpdate(patient);
                return true;
            }else {
                patientService.deleteById(patient.getId());
            }
        }

        return false;
    }
}
