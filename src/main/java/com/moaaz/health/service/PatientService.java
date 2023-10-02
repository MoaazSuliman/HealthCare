package com.moaaz.health.service;

import com.moaaz.health.dto.PatientRequest;
import com.moaaz.health.model.Patient;
import com.moaaz.health.repository.PatientRepository;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Slf4j
public class PatientService {

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private ImageService imageService;

    @Autowired
    private MailService mailService;

    public Patient createPatient(PatientRequest patientRequest) {
        Patient patient = Patient.convertPatientRequestToPatient(patientRequest);
        log.info("Patient Is Created");
        patient.setAuthentication(authenticationService.createAuthentication(patientRequest.getEmail()
                , patientRequest.getPassword()));
        patient.setImage(imageService.saveImageToFolder(patientRequest.getImageBase64()));
        log.info("Auth Is Created");
        return patientRepository.save(patient);

    }

    public List<Patient> getAll() {
        return patientRepository.findAll();
    }

    public void deleteById(long patientId) {
        getById(patientId);
        patientRepository.deleteById(patientId);
    }

    public Patient getById(long patientId) {
        return patientRepository.findById(patientId).orElseThrow(
                () -> new NoSuchElementException("There Are No Patient With Id = " + patientId)
        );
    }

    public Patient update(long patientId, PatientRequest patientRequest) {

        Patient patient = getById(patientId);
        patient.setImage(imageService.saveImageToFolder(patientRequest.getImageBase64()));
        patient.setName(patientRequest.getName());
        patient.setAddress(patientRequest.getAddress());

        return patientRepository.save(patient);
    }


    public Patient getByEmail(String email) {
        return patientRepository.findByAuthenticationEmail(email).orElse(null
        );
    }

    public Patient login(String email, String password) {
        return patientRepository.findByAuthenticationEmailAndAuthenticationPassword(email, password).orElse(null);

    }

    public void justUpdate(Patient patient) {
        patientRepository.save(patient);
    }
}
