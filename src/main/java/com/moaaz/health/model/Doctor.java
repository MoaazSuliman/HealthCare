package com.moaaz.health.model;

import com.fasterxml.jackson.databind.annotation.EnumNaming;
import com.moaaz.health.service.OtpService;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Entity
//@Table(name = "Doctors")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
//@Builder
//@EqualsAndHashCode(callSuper = true)
@DiscriminatorValue("Doctor")
public class Doctor extends Person {


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "doctor")
    private List<Clinic> clinics;
    private int stars;

    private String cardImage;

    public static Doctor convertDoctorRegisterRequestToDoctor(DoctorRegisterRequest doctorRegisterRequest) {

        Doctor doctor = new Doctor();
        doctor.setName(doctorRegisterRequest.getName());
        doctor.setCardImage(doctorRegisterRequest.getCardImage());
        doctor.setClinics(new ArrayList<>());
        return doctor;
    }

}
