package com.example.instagram.repository;

import com.example.instagram.domain.Board;
import com.fasterxml.jackson.databind.annotation.JsonAppend;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BoardRepositoryTest {

    @Autowired private BoardRepository boardRepository;

    @Test
    @Transactional
    public void makeBoard() {
        Board board = new Board();
        board.setContext("감사합니다");
        board.setHeartCount(5);
        Long saveId = boardRepository.save(board);
        Board findBoard = boardRepository.findOne(saveId);

        assertThat(board.getId()).isEqualTo(findBoard.getId());
        assertThat(board.getHeartCount()).isEqualTo(findBoard.getHeartCount());

    }


}