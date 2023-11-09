package com.lms.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lms.model.Authentication;
import com.lms.model.Level;
import com.lms.model.Teacher;
import com.lms.model.enums.Subject;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class TeacherResponse {


    protected long id;
    protected String name;

    private String address;

    private String phone;
    @JsonProperty("whatsapp_number")
    private String whatsappNumber;


    protected Authentication authentication;

    protected String image;


    protected String role;


    private Subject subject;


    private List<Level> levels;


    private List<CourseResponse> courses;

    public static TeacherResponse convertEntityToResponse(Teacher teacher) {
        return TeacherResponse
                .builder()
                .name(teacher.getName())
                .address(teacher.getAddress())
                .phone(teacher.getPhone())
                .whatsappNumber(teacher.getWhatsappNumber())
                .authentication(teacher.getAuthentication())
                .image(teacher.getImage())
                .role(teacher.getRole())
                .subject(teacher.getSubject())
                .levels(teacher.getLevels())
                .courses(teacher.getCourses().stream().map(CourseResponse::convertEntityToResponse).toList())
                .build();
    }
}
