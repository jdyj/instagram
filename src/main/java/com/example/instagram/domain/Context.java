package com.example.instagram.domain;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Context {

    @Id @GeneratedValue
    @Column(name = "context_id")
    private Long id;

    private String comment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    //==연결관계 메서드==//
    public void setBoard(Board board) {
        board.getContext().add(this);
        this.setBoard(board);
    }

}
