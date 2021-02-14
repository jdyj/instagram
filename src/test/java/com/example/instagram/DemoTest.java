package com.example.instagram;

import com.example.instagram.domain.Board;
import com.example.instagram.domain.Member;
import com.example.instagram.repository.BoardRepository;
import com.example.instagram.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;

@SpringBootTest
public class DemoTest {

    @Autowired private MemberRepository memberRepository;

    @Autowired private BoardRepository boardRepository;

    @Test
    @Transactional
    @Rollback(false)
    public void test1() {
        Member member = new Member();
        Board board = new Board();

        member.setUsername("홍길동");
        member.setAge(200);

        memberRepository.save(member);

        board.setContext("안녕하세요");
        board.setHeartCount(101);

        member.setBoard(board);
        boardRepository.save(board);
    }
}
