package com.example.instagram.service;

import com.example.instagram.domain.Context;
import com.example.instagram.repository.ContextRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class ContextServiceImpl implements ContextService {

    private final ContextRepository contextRepository;

    @Transactional
    public Long make(Context context) {
        contextRepository.save(context);
        return context.getId();
    }

}
