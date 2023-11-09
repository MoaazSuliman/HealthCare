package com.lms.controller;

import com.lms.dto.VideoRequest;
import com.lms.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("lms/api/v1/videos")
public class VideoController {

    @Autowired
    private VideoService videoService;

    @PostMapping
    public ResponseEntity<?> addVideo(@ModelAttribute VideoRequest videoRequest) {
        return new ResponseEntity<>(videoService.addVideo(videoRequest), HttpStatus.CREATED);
    }


    @DeleteMapping("/{videoId}")
    public ResponseEntity<?> deleteVideoById(@PathVariable long videoId) {
        return new ResponseEntity<>(videoService.deleteById(videoId), HttpStatus.ACCEPTED);
    }


}
