package com.example.instagram.dto;

import com.example.instagram.domain.Context;
import com.example.instagram.domain.Image;
import com.example.instagram.domain.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

@NoArgsConstructor
@Getter @Setter
public class BoardDto {

    private Long id;

    private String description;

    private List<Context> context;

    private int heartCount = 0;

    private List<Image> images;

    private Member member;
}
