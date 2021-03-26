package com.example.instagram.service;

import com.example.instagram.domain.Board;
import com.example.instagram.domain.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BoardServiceTest {

    @Autowired MemberService memberService;
    @Autowired BoardService boardService;


    @Test
    @Transactional
    public void 연관관계() throws Exception {

        Member member = new Member();
        member.setAge(10);
        member.setUsername("asdf");
        member.setEmail("df");
        member.setPassword("df");
        member.setComment("asdf");
        Long memberId = memberService.join(member);

        Board board = new Board();
        board.setMember(memberService.findOne(memberId));

        boardService.make(board);
    }

}