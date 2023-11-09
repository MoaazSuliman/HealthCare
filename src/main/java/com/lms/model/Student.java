package com.lms.model;

import com.lms.dto.StudentRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
//@Table(name = "students")
@DiscriminatorValue("Student")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Student extends Person {

    private String level;

    public static Student convertDtoToEntity(StudentRequest studentRequest) {
        Student student = new Student();
        student.setName(studentRequest.getName());
        student.setAddress(studentRequest.getAddress());
        student.setPhone(studentRequest.getPhone());
        student.setWhatsappNumber(studentRequest.getWhatsappNumber());
        student.setRole("STUDENT");
        student.setAuthentication(new Authentication(0, studentRequest.getEmail(), studentRequest.getPassword(), "No Oto", true));
        student.setLevel(studentRequest.getLevel());
        return student;
    }

}
