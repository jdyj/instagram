package com.example.instagram.domain;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter @Setter
public class Image {

    @Id
    @GeneratedValue
    @Column(name = "image_id")
    private Long id;

    private String origFilename;

    private String filename;

    private String filePath;
}
