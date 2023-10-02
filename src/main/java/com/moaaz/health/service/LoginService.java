package com.moaaz.health.service;

import com.moaaz.health.model.Doctor;
import com.moaaz.health.model.Patient;
import com.moaaz.health.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private DoctorService doctorService;
    @Autowired
    private PatientService patientService;

    @Autowired
    private MailService mailService;

    public ResponseEntity<?> login(String email, String password) {
        Doctor doctor = doctorService.login(email, password);
        if (doctor != null) {
            if (doctor.getAuthentication().isVerify()) {
                return new ResponseEntity<>(doctor, HttpStatus.ACCEPTED);
            } else
                return new ResponseEntity<>("This Doctor Not Verify His OTP", HttpStatus.BAD_REQUEST);
        }
        Patient patient = patientService.login(email, password);
        if (patient != null) {
            if (patient.getAuthentication().isVerify()) {
                return new ResponseEntity<>(patient, HttpStatus.ACCEPTED);
            } else
                return new ResponseEntity<>("This Doctor Not Verify His OTP", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Error In Email Or Password", HttpStatus.BAD_REQUEST);
    }


    public ResponseEntity<?> forgetPassword(String email) {
        Doctor doctor = doctorService.getByEmail(email);
        if (doctor != null) {
            mailService.sendPasswordToEmail(email, doctor.getAuthentication().getPassword());
            return new ResponseEntity<>("Password Sent Successfully", HttpStatus.ACCEPTED);
        }
        Patient patient = patientService.getByEmail(email);
        if (patient != null) {
            mailService.sendPasswordToEmail(email, patient.getAuthentication().getPassword());
            return new ResponseEntity<>("Password Sent Successfully", HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>("Error In Email", HttpStatus.BAD_REQUEST);
    }
}


