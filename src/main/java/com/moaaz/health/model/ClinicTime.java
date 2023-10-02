package com.moaaz.health.model;

import com.moaaz.health.model.enums.Day;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table
@Data

public class ClinicTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String fromTime;
    private String toTime;

    private Day day;

}
