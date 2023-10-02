package com.moaaz.health.controller;

import com.moaaz.health.dto.PatientRequest;
import com.moaaz.health.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/moaaz/api/health/patients")
@RestController
public class PatientController {

    @Autowired
    private PatientService patientService;

    @GetMapping
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(patientService.getAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> add(@RequestBody PatientRequest patientRequest) {
        return new ResponseEntity<>(patientService.createPatient(patientRequest), HttpStatus.CREATED);
    }

    @PutMapping("/{patientId}")
    public ResponseEntity<?>update(@PathVariable long patientId , @RequestBody PatientRequest patientRequest){
        return new ResponseEntity<>(patientService.update(patientId , patientRequest) , HttpStatus.ACCEPTED);
    }
    @DeleteMapping("/{patientId}")
    public ResponseEntity<?> deleteById(@PathVariable long patientId) {
        patientService.deleteById(patientId);
        return new ResponseEntity<>("Deleted Successfully", HttpStatus.ACCEPTED);
    }

}
