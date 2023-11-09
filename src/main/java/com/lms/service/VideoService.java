package com.lms.service;

import com.lms.dto.VideoRequest;
import com.lms.model.Video;
import com.lms.repository.VideoRepository;
import com.lms.service.files.FileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.NoSuchElementException;

@Service
@Slf4j
public class VideoService {

    @Autowired
    private VideoRepository videoRepository;

    @Autowired
    private CourseVideoService courseVideoService;

    @Autowired
    private FileService fileService;

    @Transactional(rollbackFor = Exception.class)//if any exception happens don't do anything
    public Video addVideo(VideoRequest videoRequest) {

        Video video = Video.convertVideoRequestToVideo(videoRequest);
        // 1- upload video to s3.
        video.setVideoUrl(fileService.uploadVideoToS3AndGetImageUrl(videoRequest.getVideoFile()));
        log.info(video.getVideoUrl());
        // 2- assign course to the video.
        courseVideoService.assignVideoToCourse(videoRequest.getCourseId(), video);
        // 3- save video in the database.
        video.setCreationDate(LocalDate.now());
        return videoRepository.save(video);
    }


    // delete video
    public String deleteById(long videoId) {
        getById(videoId);
        videoRepository.deleteById(videoId);
        return "Deleted Successfully";
    }

    public Video getById(long videoId) {
        return videoRepository.findById(videoId)
                .orElseThrow(
                        () -> new NoSuchElementException("There Are No Video With This Id ")
                );
    }


}
