package com.lms.model;

import com.lms.dto.TeacherRequest;
import com.lms.model.enums.Subject;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@DiscriminatorValue("Teacher")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Teacher extends Person {



//    @Enumerated(EnumType.STRING)
//    @OneToOne
    private Subject subject;

    @ManyToMany
    private List<Level>levels;


    @OneToMany(mappedBy = "teacher")
    private List<Course> courses;


    public static Teacher convertTeacherRegisterRequestToTeacher(TeacherRequest teacherRequest) {

        Teacher teacher = new Teacher();

        teacher.setName(teacherRequest.getName());
        teacher.setAddress(teacherRequest.getAddress());
//        teacher.setImage(teacherRequest.getImageBase64());
        teacher.setPhone(teacherRequest.getPhone());
        teacher.setWhatsappNumber(teacherRequest.getWhatsappNumber());
        teacher.setSubject(teacherRequest.getSubject());

        teacher.setRole("TEACHER");

        return teacher;

    }

}
