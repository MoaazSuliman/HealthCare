package com.moaaz.health.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class  PatientRequest {


    private  String name;

    private String address;
    private String email ;
    private String password;
    private String imageBase64;

}
