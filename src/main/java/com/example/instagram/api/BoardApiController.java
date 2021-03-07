package com.example.instagram.api;


import com.example.instagram.domain.Board;
import com.example.instagram.domain.Context;
import com.example.instagram.domain.Member;
import com.example.instagram.service.*;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class BoardApiController {

    private final BoardService boardService;

    private final MemberService memberService;

    private final ContextService contextService;

    @ApiOperation(value = "게시판 생성")
    @PostMapping("/api/v2/boards")
    public CreateBoardResponse make(
            @RequestBody @Valid CreateBoardRequest request) {
        Board board = new Board();
        board.setHeartCount(request.getHeartCount());
        board.setDescription(request.getDescription());
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
        private String description;
        private int heartCount;
        private Long memberId;
    }

    @ApiOperation(value = "내 게시판 조회")
    @GetMapping("/myPage/v1/member/{id}/boards")
    public ShowMyBoardResponse myPageBoard(@PathVariable("id") Long memberId) {
        Member member = memberService.findOne(memberId);
        List<Board> findBoards = member.getBoards();
        List<BoardDto> collect = findBoards.stream()
                .map(board -> new BoardDto(board.getDescription(), board.getHeartCount()))
                .collect(Collectors.toList());

        return new ShowMyBoardResponse(collect);
    }

    @Data
    @AllArgsConstructor
    static class ShowMyBoardResponse<T> {
        private T data;
    }

    @Data
    @AllArgsConstructor
    static class BoardDto {
        private String description;
        private int heartCount;
    }

    @PostMapping("/api/v1/contexts")
    public ResponseEntity<ContextResponse> makeContextV1(@RequestBody @Valid ContextRequest request) {

        Context context = new Context();
        context.setComment(request.getComment());
        context.setMember(memberService.findOne(request.getMemberId()));
        context.setBoard(boardService.findOne(request.getBoardId()));

        Long contextId = contextService.make(context);

        return ResponseEntity.ok().body(new ContextResponse(contextId));
    }

    @Data
    static class ContextRequest {
        private String comment;
        private Long memberId;
        private Long boardId;
    }

    @AllArgsConstructor
    @Data
    static class ContextResponse {
        private Long contextId;
    }



}
