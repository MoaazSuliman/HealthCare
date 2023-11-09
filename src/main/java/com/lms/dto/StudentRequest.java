package com.lms.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StudentRequest {

    protected String name;

    private String address;

    private String phone;

    private String whatsappNumber;

    private String email;

    private String password;

    private String level;


}
