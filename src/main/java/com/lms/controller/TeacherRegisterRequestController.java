package com.lms.controller;

import com.lms.service.TeacherRegisterService;
import com.lms.model.TeacherRegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/teacherRegister")
@RestController
@CrossOrigin("*")
public class TeacherRegisterRequestController {


    @Autowired
    private TeacherRegisterService teacherRegisterService;


    @PostMapping
    public ResponseEntity<?> createTeacherRegisterRequest(@RequestBody TeacherRegisterRequest teacherRegisterRequest) {
        return new ResponseEntity<>(teacherRegisterService.createNewRegisterRequestForTeacher(teacherRegisterRequest),
                HttpStatus.CREATED);

    }

    @PostMapping("/accept/{requestId}")
    public ResponseEntity<?> acceptRegisterRequest(@PathVariable long requestId) {
        return new ResponseEntity<>(teacherRegisterService.acceptTeacherRequest(requestId), HttpStatus.ACCEPTED);

    }


    @PostMapping("/reject/{requestId}")
    public ResponseEntity<?> rejectRegisterRequest(@PathVariable long requestId, @RequestParam String causeMessage) {
        return new ResponseEntity<>(teacherRegisterService.rejectTeacherRequest(requestId, causeMessage)
                , HttpStatus.ACCEPTED);
    }

    @GetMapping
    public ResponseEntity<?> getAllRegistersThatAreInWaiting() {
        return new ResponseEntity<>(teacherRegisterService.getAllRegistersThatAreInWaiting()
                , HttpStatus.OK);
    }

    @GetMapping("/{requestId}")
    public ResponseEntity<?> getById(@PathVariable long requestId) {
        return new ResponseEntity<>(teacherRegisterService.getById(requestId), HttpStatus.ACCEPTED);
    }


}
