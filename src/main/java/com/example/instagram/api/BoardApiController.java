package com.example.instagram.api;


import com.example.instagram.domain.Board;
import com.example.instagram.domain.Member;
import com.example.instagram.service.BoardService;
import com.example.instagram.service.BoardServiceImpl;
import com.example.instagram.service.MemberService;
import com.example.instagram.service.MemberServiceImpl;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class BoardApiController {

    private final BoardService boardService;

    private final MemberService memberService;

    @ApiOperation(value = "게시판 생성")
    @PostMapping("/api/v2/boards")
    public CreateBoardResponse make(
            @RequestBody @Valid CreateBoardRequest request) {
        Board board = new Board();
        board.setHeartCount(request.getHeartCount());
//        board.setContext(request.getContext());
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

}
