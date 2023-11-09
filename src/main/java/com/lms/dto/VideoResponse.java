package com.lms.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lms.model.Video;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;


@Data
@Builder
public class VideoResponse {


    private long id;
    private String name;
    private String description;

    @JsonProperty("video_url")
    private String videoUrl;

    @JsonProperty("creation_date")
    private LocalDate creationDate;


    public static VideoResponse convertEntityToResponse(Video video) {
        return VideoResponse.builder()
                .id(video.getId())
                .videoUrl(video.getVideoUrl())
                .creationDate(video.getCreationDate())
                .description(video.getDescription())
                .name(video.getName())
                .build();
    }


}
