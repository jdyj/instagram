package com.example.instagram.service;

import com.example.instagram.domain.Image;
import com.example.instagram.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;

    @Transactional
    public Long saveFile(Image image) {
        return imageRepository.save(image);
    }
}
