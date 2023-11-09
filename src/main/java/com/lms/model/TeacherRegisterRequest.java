package com.lms.model;


import com.lms.model.enums.RegisterStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Table
@Data
public class TeacherRegisterRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    @NotNull(message = "Email Can't Be Null")
    @NotEmpty(message = "Email Can't Be Null")
    private String email;
    @NotNull(message = "Password Can't Be Null")
    @NotEmpty(message = "Password  Can't Be Null")
    private String password;
    @NotNull(message = "Card Image Can't Be Null")
    @NotEmpty(message = "Card Image Can't Be Null")
    private String cardImage;

    private RegisterStatus registerStatus;

}
