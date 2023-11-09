package com.lms.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lms.dto.VideoRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "videos")
public class Video {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String description;
    private String videoUrl;

    private LocalDate creationDate;


    @ManyToOne
    @JsonIgnore
    private Course course;

    public static Video convertVideoRequestToVideo(VideoRequest videoRequest) {
        Video  video= new Video();
        video.setName(videoRequest.getName());
        video.setDescription(videoRequest.getDescription());
        return video;
    }
}
