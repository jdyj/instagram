package com.example.instagram.api;

import com.example.instagram.domain.Gender;
import com.example.instagram.domain.Member;
import com.example.instagram.service.MemberService;
import com.example.instagram.service.MemberServiceImpl;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.stream.Collectors;

// @Controller @ResponseBody
@RestController
@RequiredArgsConstructor
public class MemberApiController {

    private final MemberService memberService;

    //회원 가입
    @PostMapping("/signUp/v1/member")
    public SignUpMemberResponse memberSignUpV1(@RequestBody @Valid SignUpMemberRequest request) {
        Member member = new Member();
        member.setUsername(request.getName());
        member.setAge(request.getAge());
        member.setEmail(request.getEmail());
        member.setGender(request.getGender());
        member.setPassword(request.getPassword());
        Long id = memberService.join(member);
        return new SignUpMemberResponse(id);
    }

    //로그인
    @PostMapping("/signIn/v1/member")
    public SignInMemberResponse memberSignInV1(@RequestBody @Valid SignInMemberRequest request) {
        Member member = memberService.signIn(request.getEmail(), request.getPassword());
        return new SignInMemberResponse(member.getId());
    }

    @Data
    static class SignInMemberRequest {
        private String email;
        private String password;
    }

    @Data
    @AllArgsConstructor
    static class SignInMemberResponse {
        private Long memberId;
    }


    @Data
    @AllArgsConstructor
    static class Result<T> {
        private int count;
        private T data;
    }


    /**
     * 등록 V2 : 요청 값으로 Member 엔티티 대신 별도의 DTO를 받는다.
     */
    @PutMapping("/api/v2/members/{id}")
    public UpdateMemberResponse updateMemberV2(
            @PathVariable("id") Long id,
            @RequestBody @Valid UpdateMemberRequest request) {

        memberService.update(id,request.getName());
        Member findMember = memberService.findOne(id);
        return new UpdateMemberResponse(findMember.getId(),findMember.getUsername());
    }

    @Data
    static class UpdateMemberRequest {
       private String name;

       private int age;

       @Enumerated(EnumType.STRING)
       private Gender gender;
    }

    @Data
    @AllArgsConstructor
    static class UpdateMemberResponse {
        private Long id;
        private String name;
    }



    @Data
    static class SignUpMemberRequest {
        @NotEmpty
        private String email;
        @NotEmpty
        private String password;
        @NotEmpty
        private String name;
        private int age;
        private Gender gender;
    }

    @Data
    @AllArgsConstructor
    static class SignUpMemberResponse {
        private Long id;
    }
}
