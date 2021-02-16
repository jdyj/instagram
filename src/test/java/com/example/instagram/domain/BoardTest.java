package com.example.instagram.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

    @Test
    public void 하트증가() throws Exception {
        //given
        Board board = new Board();
        board.setContext("안녕하세요");
        board.setHeartCount(5);

        //when
        int addHeartCount = board.addHeartCount();

        //then
        assertThat(addHeartCount).isEqualTo(6);
    }

}