package com.example.instagram.domain;


import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Context {

    @Id @GeneratedValue
    @Column(name = "context_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;


}
