package com.lms.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class VideoRequest {

    @NotNull(message = "Name Must Not Be Null")
    @NotEmpty(message = "Name Must Not Be Empty")
    private String name;


    private String description;

    @NotNull(message = "video_file Must Not Be Null")
    @JsonProperty("video_profile")
    private MultipartFile videoFile;



    @NotNull(message = "course_id Must Not Be Null")
    @JsonProperty("course_id")
    private long courseId;
}
