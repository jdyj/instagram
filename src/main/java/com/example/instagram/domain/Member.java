package com.example.instagram.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String email;

    private String username;

    private String password;

    private String comment;

    private int age;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "member")
    private List<Board> boards = new ArrayList<>();

    @OneToMany
    private List<Member> followers = new ArrayList<>();

    @OneToMany
    private List<Member> followings = new ArrayList<>();

}