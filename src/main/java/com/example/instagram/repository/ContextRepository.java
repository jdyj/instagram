package com.example.instagram.repository;


import com.example.instagram.domain.Context;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ContextRepository {

    private final EntityManager em;

    public Long save(Context context) {
        em.persist(context);
        return context.getId();
    }

    public List<Context> findAll() {
        return em.createQuery("select c from Context c", Context.class)
                .getResultList();
    }
}
