package com.lms.controller;

import com.lms.service.CourseService;
import com.lms.dto.CourseRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("lms/api/v1/courses")
public class CourseController {
    @Autowired
    private CourseService courseService;


    @PostMapping
    public ResponseEntity<?> addCourse(@RequestBody CourseRequest courseRequest) {
        return new ResponseEntity<>(courseService.createCourse(courseRequest), HttpStatus.CREATED);
    }


    @GetMapping("/getAll")
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(courseService.getAll(), HttpStatus.OK);
    }


}
