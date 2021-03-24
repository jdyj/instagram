package com.example.instagram.service;

import com.example.instagram.domain.Image;
import org.springframework.web.multipart.MultipartFile;

public interface ImageService {

    Long saveFile(Image image);

    void restore(MultipartFile[] files);
}
