package com.lms.controller;

import com.lms.dto.TeacherRequest;
import com.lms.service.TeacherService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("lms/api/v1/teachers")
@RestController
//@CrossOrigin("*")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @GetMapping
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(teacherService.getAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> add(@RequestBody @Valid TeacherRequest teacherRequest) {
        return new ResponseEntity<>(teacherService.createTeacher(teacherRequest), HttpStatus.CREATED);
    }

    @PutMapping("/{teacherId}")
    public ResponseEntity<?> update(@PathVariable long teacherId, @RequestBody TeacherRequest teacherRequest) {
        return new ResponseEntity<>(teacherService.update(teacherId, teacherRequest), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{teacherId}")
    public ResponseEntity<?> deleteById(@PathVariable long teacherId) {
        teacherService.deleteById(teacherId);
        return new ResponseEntity<>("Deleted Successfully", HttpStatus.ACCEPTED);
    }

}
