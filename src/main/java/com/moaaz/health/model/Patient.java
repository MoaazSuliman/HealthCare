package com.moaaz.health.model;

import com.moaaz.health.dto.PatientRequest;
import com.moaaz.health.model.enums.Role;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Builder;
import lombok.Data;

@Entity
//@Table(name = "patients")
@Data
@DiscriminatorValue("Patient")
@Builder
public class Patient extends Person {


    public static Patient convertPatientRequestToPatient(PatientRequest patientRequest) {

        Patient patient = new Patient();

        patient.setName(patientRequest.getName());
        patient.setAddress(patientRequest.getAddress());
        patient.setImage(patientRequest.getImageBase64());
        patient.setRole(Role.PATIENT);
        System.out.println("Creating Patient Successfully");
        return patient;

    }

}
