package com.example.instagram.api;


import com.example.instagram.domain.Board;
import com.example.instagram.service.BoardService;
import com.example.instagram.service.BoardServiceImpl;
import com.example.instagram.service.MemberService;
import com.example.instagram.service.MemberServiceImpl;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class BoardApiController {

    private final BoardService boardService;

    private final MemberService memberService;

    @PostMapping("/api/v2/boards")
    public CreateBoardResponse make(
            @RequestBody @Valid CreateBoardRequest request) {
        Board board = new Board();
        board.setHeartCount(request.getHeartCount());
        board.setContext(request.getContext());
        board.setMember(memberService.findOne(request.getMemberId()));
        Long boardId = boardService.make(board);
        return new CreateBoardResponse(boardId);
    }

    @Data
    @AllArgsConstructor
    static class CreateBoardResponse {
        private Long boardId;
    }

    @Data
    static class CreateBoardRequest {
        private String context;
        private int heartCount;
        private Long memberId;
    }
}