package com.example.instagram.service;


import com.example.instagram.domain.Board;
import com.example.instagram.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{

    private final BoardRepository boardRepository;

    @Transactional
    public Long make(Board board) {
        boardRepository.save(board);
        return board.getId();
    }
}
