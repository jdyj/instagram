package com.example.instagram.service;

import com.example.instagram.domain.Board;
import org.springframework.stereotype.Service;


public interface BoardService {

    Long make(Board board);

    Board findOne(Long boardId);
}
