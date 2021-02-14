package com.example.instagram.service;

import com.example.instagram.domain.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;


import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberServiceTest {

    @Autowired MemberService memberService;

    @Test
    @Transactional
    public void 중복회원검증() throws Exception {
        //given
        Member member1 = new Member();
        Member member2 = new Member();
        member1.setUsername("userA");
        member2.setUsername("userA");

        //when
        memberService.join(member1);

        //then
        assertThrows(IllegalStateException.class,
                () -> memberService.join(member2));

    }
}