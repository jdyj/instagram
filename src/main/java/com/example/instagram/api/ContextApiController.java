package com.example.instagram.api;

import com.example.instagram.service.ContextService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ContextApiController {

    private final ContextService contextService;



}
