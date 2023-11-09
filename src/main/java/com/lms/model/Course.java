package com.lms.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lms.dto.CourseRequest;
import com.lms.model.enums.Subject;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private Subject subject;

    private String description;
    private LocalDate creationDate;
    private LocalDate lastUpdate;

    @ManyToOne
    @JsonIgnore
    private Teacher teacher;


    @ManyToOne
    private Level level;

    @OneToMany(mappedBy = "course")
    private List<Video>videos;

    public static Course convertCourseRequstToCourse(CourseRequest courseRequest) {
        Course course = new Course();
        course.setSubject( courseRequest.getSubject());
        course.setDescription(courseRequest.getDescription());
        course.setLastUpdate(LocalDate.now());
        return course;
    }

    // TODO Lessons Will Have Videos and Online Exams.
//    private List<Lesson>lessons;

}
