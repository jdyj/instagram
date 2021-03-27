package com.example.instagram.service;

import com.example.instagram.domain.Board;
import com.example.instagram.domain.Member;
import org.springframework.stereotype.Service;

import java.util.List;


public interface BoardService {

    Long make(Board board);

    Board findOne(Long boardId);

    List<Board> findMyBoards(Member member);
}
