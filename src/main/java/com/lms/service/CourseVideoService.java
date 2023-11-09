package com.lms.service;

import com.lms.model.Course;
import com.lms.model.Video;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseVideoService {


    @Autowired
    private CourseService courseService;

    // assign video to course.
    public void assignVideoToCourse(long courseId , Video video){
        Course course= courseService.getById(courseId);
        video.setCourse(course);
    }

    // check if video is assigned to this course or not before deleting this course

}
