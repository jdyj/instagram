package com.example.instagram.service;

import com.example.instagram.domain.Member;
import com.example.instagram.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    /**
     * 회원가입
     */

    @Transactional
    public Long join(Member member) {
        validateDuplicateMember(member); // 중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    public void validateDuplicateMember(Member member) {
        List<Member> findMembers =
            memberRepository.findByEmail(member.getEmail());

        if(findMembers.size() > 0) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }

    // 이렇게 해도 될련지 모르겠네..
    public Member signIn(String email, String password) {
        List<Member> findMembers = memberRepository.findByEmailAndPassword(email, password);
        if(findMembers.size() == 0) {
            throw new IllegalStateException("존재하지 않는 회원입니다");
        }
        return findMembers.get(0);
    }

    @Transactional
    public void update(Long id, String name) { //커맨드와 쿼리를 철저히 분리
        Member member = memberRepository.findOne(id);
        member.setUsername(name);
    }
}
