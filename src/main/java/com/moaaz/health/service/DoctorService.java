package com.moaaz.health.service;

import com.moaaz.health.model.Doctor;
import com.moaaz.health.model.DoctorRegisterRequest;
import com.moaaz.health.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.NoSuchElementException;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private OtpService otpService;

    public Doctor createDoctor(DoctorRegisterRequest doctorRegisterRequest) {
        Doctor doctor = Doctor.convertDoctorRegisterRequestToDoctor(doctorRegisterRequest);
        doctor.setAuthentication(authenticationService.createAuthentication(doctorRegisterRequest.getEmail(), doctorRegisterRequest.getPassword()));

        return doctorRepository.save(doctor);
    }


    public Doctor getByEmail(String email) {
        return doctorRepository.findByAuthenticationEmail(email).orElse(null);
    }

    public Doctor login(String email, String password) {
        return doctorRepository.findByAuthenticationEmailAndAuthenticationPassword(email, password).orElse(null);
    }

    public Doctor getById(long id) {
        return doctorRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("This Id Are Not In Our database.")
        );
    }

    public void deleteById(long id) {
        getById(id);
        doctorRepository.deleteById(id);
    }

    public void justUpdate(Doctor doctor) {
        doctorRepository.save(doctor);
    }
}
