package com.lms.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lms.model.Course;
import com.lms.model.Level;
import com.lms.model.enums.Subject;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class CourseResponse {


    private long id;

    private Subject subject;

    private String description;

    @JsonProperty("creation_date")
    private LocalDate creationDate;
    @JsonProperty("last_update")
    private LocalDate lastUpdate;


    @JsonProperty("teacher_name")
    private String teacherName;


    private Level level;

    private List<VideoResponse> videos;


    public static CourseResponse convertEntityToResponse(Course course) {
        if(course.getVideos()==null)
            course.setVideos(new ArrayList<>());
        return CourseResponse
                .builder()
                .id(course.getId())
                .subject(course.getSubject())
                .description(course.getDescription())
                .creationDate(course.getCreationDate())
                .lastUpdate(course.getLastUpdate())
                .teacherName(course.getTeacher().getName())
                .level(course.getLevel())
                .videos(course.getVideos().stream().map(VideoResponse::convertEntityToResponse).toList())
                .build();
    }
}
