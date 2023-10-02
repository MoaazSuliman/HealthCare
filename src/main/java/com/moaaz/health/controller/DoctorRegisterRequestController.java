package com.moaaz.health.controller;

import com.moaaz.health.model.DoctorRegisterRequest;
import com.moaaz.health.service.DoctorRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/moaaz/api/health/doctorRegisterRequests")
@RestController
public class DoctorRegisterRequestController {


    @Autowired
    private DoctorRegisterService doctorRegisterService;


    @PostMapping
    public ResponseEntity<?> createDoctorRegisterRequest(@RequestBody DoctorRegisterRequest doctorRegisterRequest) {
        return new ResponseEntity<>(doctorRegisterService.createNewRegisterRequestForDoctor(doctorRegisterRequest),
                HttpStatus.CREATED);

    }

    @PostMapping("/accept/{requestId}")
    public ResponseEntity<?> acceptRegisterRequest(@PathVariable long requestId) {
        return new ResponseEntity<>(doctorRegisterService.acceptDoctorRequest(requestId), HttpStatus.ACCEPTED);

    }


    @PostMapping("/reject/{requestId}")
    public ResponseEntity<?> rejectRegisterRequest(@PathVariable long requestId, @RequestParam String causeMessage) {
        return new ResponseEntity<>(doctorRegisterService.rejectDoctorRequest(requestId, causeMessage)
                , HttpStatus.ACCEPTED);
    }

    @GetMapping
    public ResponseEntity<?> getAllRegistersThatAreInWaiting() {
        return new ResponseEntity<>(doctorRegisterService.getAllRegistersThatAreInWaiting()
                , HttpStatus.OK);
    }

    @GetMapping("/{requestId}")
    public ResponseEntity<?> getById(@PathVariable long requestId) {
        return new ResponseEntity<>(doctorRegisterService.getById(requestId), HttpStatus.ACCEPTED);
    }


}
