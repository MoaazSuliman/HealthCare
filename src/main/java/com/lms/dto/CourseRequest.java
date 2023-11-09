package com.lms.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lms.model.enums.Subject;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CourseRequest {

    @NotNull(message = "subject must not be null")
    private Subject subject;

    private String description;


    @NotNull(message = "teacher_id Must Not Be Null")
    @JsonProperty("teacher_id")
    private long teacherId;

    @NotNull(message = "level_id Must Not Be Null")
    @JsonProperty("level_id")
    private long levelId;


}
