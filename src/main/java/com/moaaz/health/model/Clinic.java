package com.moaaz.health.model;

import com.moaaz.health.model.enums.Day;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Clinic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id ;

    @OneToMany(cascade = CascadeType.ALL)
    private List<ClinicTime>clinicTimes;

    private String clinicLocation;

    private double clinicPrice;

    @ManyToOne
    private Doctor doctor;
}
