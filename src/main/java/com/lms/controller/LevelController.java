package com.lms.controller;

import com.lms.model.Level;
import com.lms.service.LevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("lms/api/v1/levels")
public class LevelController {
    @Autowired
    private LevelService levelService;


    @PostMapping
    public ResponseEntity<?>add(@RequestBody Level level){
        return new ResponseEntity<>(levelService.add(level) , HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(levelService.getAllLevels(), HttpStatus.OK);
    }
}
