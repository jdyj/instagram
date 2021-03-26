package com.example.instagram.domain;

import com.example.instagram.repository.MemberRepository;
import com.example.instagram.service.BoardService;
import com.example.instagram.service.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

    @Test
    public void 하트증가() throws Exception {
        //given
        Board board = new Board();
//        board.setContext("안녕하세요");
        board.setHeartCount(5);

        //when
        int addHeartCount = board.addHeartCount();

        //then
        assertThat(addHeartCount).isEqualTo(6);
    }


    @Test
    public void 게시판_생성() throws Exception {

        Board board = new Board();
//        board.setContext("해위");
        board.setHeartCount(5);

    }

}