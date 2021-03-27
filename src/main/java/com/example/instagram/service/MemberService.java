package com.example.instagram.service;


import com.example.instagram.domain.Member;

import java.util.List;

public interface MemberService {

    //회원가입
    Long join(Member member);

    //전체 회원 조회
    List<Member> findMembers();

    //회원 단건 조회
    Member findOne(Long memberId);

    void update(Long id, String name);

    void follow(Long id, String findName);

    Member signIn(String email, String password);

    Member searchName(String name);
}
