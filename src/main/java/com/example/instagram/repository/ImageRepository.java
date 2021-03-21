package com.example.instagram.repository;

import com.example.instagram.domain.Image;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class ImageRepository {

    private final EntityManager em;

    public Long save(Image image) {
        em.persist(image);
        return image.getId();
    }
}
