package com.example.instagram.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter @Setter
public class Board {

    @Id @GeneratedValue
    @Column(name = "board_id")
    private Long id;

    private String description;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "board")
    private List<Context> context;

    private int heartCount = 0;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Image> images;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    //==비즈니스 로직==//
    public int addHeartCount() {
        return this.heartCount += 1;
    }

    //==연관관계 메서드==//
    public void setMember(Member member) {
        member.getBoards().add(this);
        this.member = member;
        addHeartCount();
    }
}
