package com.example.instagram.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.persistence.criteria.Fetch;
import javax.validation.constraints.NotEmpty;
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

}
