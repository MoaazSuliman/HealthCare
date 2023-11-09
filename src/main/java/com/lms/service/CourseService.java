package com.lms.service;

import com.lms.model.Course;
import com.lms.dto.CourseRequest;
import com.lms.dto.CourseResponse;
import com.lms.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private TeacherCourseService courseAssignService;

    public CourseResponse createCourse(CourseRequest courseRequest) {
        Course course = Course.convertCourseRequstToCourse(courseRequest);
        course.setCreationDate(LocalDate.now());
        // assign course to the teacher
        courseAssignService.assignCourseToTeacher(courseRequest.getTeacherId(), course);
        // assign course to level
        courseAssignService.assignCourseToLevel(courseRequest.getLevelId(), course);
        // check if this teacher can give this level or not.
        courseAssignService.checkIfThisTeacherCanGiveThisLevelOrNotBeforeAddingAnyCourseForThisLevel(course);

        // save course then convert it to course response
        return CourseResponse.convertEntityToResponse(courseRepository.save(course));
    }

    public Course getById(long courseId) {
        return courseRepository.findById(courseId).orElseThrow(
                () -> new NoSuchElementException("There Are No Course With This ID ")
        );
    }


    public List<CourseResponse> getAll() {
        return courseRepository.findAll().stream().map(CourseResponse::convertEntityToResponse).toList();
    }
}
