package com.lms.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lms.model.enums.Subject;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherRequest {


    @NotNull(message = "Name Must Be Null")
    @NotEmpty(message = "Name Must Not Be Empty")
    private  String name;

    private String address;


    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@(.+)$", message = "Invalid email address")
    @NotNull(message = "Email Must Be Null")
    @NotEmpty(message = "Email Must Not Be Empty")
    private String email ;


    @NotNull(message = "Subject Must Be Null")
    private Subject subject;

    private String phone;

    @JsonProperty("whatsapp_number")
    private String whatsappNumber;


    @NotNull(message = "Password Must Be Null")
    @NotEmpty(message = "Password Must Not Be Empty")
    private String password;



    @NotNull(message = "Levels Must Be Null")
    private List<Long>levels;


}
