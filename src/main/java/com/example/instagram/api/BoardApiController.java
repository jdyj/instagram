package com.example.instagram.api;


import com.example.instagram.domain.Board;
import com.example.instagram.domain.Context;
import com.example.instagram.domain.Image;
import com.example.instagram.domain.Member;
import com.example.instagram.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class BoardApiController {

    private final BoardService boardService;

    private final MemberService memberService;

    private final ContextService contextService;

    private final ImageService imageService;

    @ApiOperation(value = "게시판 생성")
    @PostMapping(value = "/api/v2/boards")
    public CreateBoardResponse make(
            MultipartHttpServletRequest request) throws IOException {

        System.out.println("파일업로드테스트");
        String heartCount = request.getParameter("heartCount");
        String memberId = request.getParameter("memberId");

        Board board = new Board();
        board.setHeartCount(Integer.parseInt(heartCount));
        board.setDescription(request.getParameter("description"));
        Member member = memberService.findOne(Long.parseLong(memberId));
        board.setMember(member);

        String path = "C:\\Image";
        File folder = new File(path);
        if(!folder.exists()) {
            folder.mkdir();
            System.out.println("폴더가 생성되었습니다");
        }
        List<MultipartFile> files = request.getFiles("files");
        List<Image> images = new ArrayList<>();
        for(MultipartFile file : files) {

            Image image = new Image();

            String originalFileName = file.getOriginalFilename();
            if(!new File("/Image").exists()) {
                new File("/Image").mkdirs();
            }
            File dest = new File("C:/Image/" + originalFileName);
            file.transferTo(dest);
            image.setFilename(originalFileName);
            image.setOrigFilename(originalFileName);
            image.setFilePath("/Image");
            imageService.saveFile(image);
            images.add(image);

        }
        board.setImages(images);
        Long boardId = boardService.make(board);
        return new CreateBoardResponse(boardId);
    }

    @Data
    @AllArgsConstructor
    static class CreateBoardResponse {
        private Long boardId;
    }

    @ApiOperation(value = "피드 조회")
    @GetMapping("/api/v1/member/{id}/boards")
    public Result friendBoard(@PathVariable("id") Long memberId) {
        Member member = memberService.findOne(memberId);
        List<Member> followingMembers = member.getFollowings();
        List<BoardDto> collect = null;
        for (Member followingMember : followingMembers) {
            List<Board> followingBoards = boardService.findMyBoards(followingMember);
            collect = followingBoards.stream()
                    .map(board -> new BoardDto(board.getId(), board.getImages(), board.getDescription(), board.getHeartCount()))
                    .collect(Collectors.toList());
        }
        return new Result(collect);
    }

    @AllArgsConstructor
    static class Result<T> {
        private T data;

    }


    @ApiOperation(value = "내 게시판 조회")
    @GetMapping("/myPage/v1/member/{id}/boards")
    public ShowMyBoardResponse myPageBoard(@PathVariable("id") Long memberId) {
        Member member = memberService.findOne(memberId);
        List<Board> findBoards = boardService.findMyBoards(member);
        List<BoardDto> collect = findBoards.stream()
                .map(board -> new BoardDto(board.getId(), board.getImages(), board.getDescription(), board.getHeartCount()))
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
        private Long boardId;
        private List<Image> image;
        private String description;
        private int heartCount;
    }

    @ApiOperation(value = "댓글 생성")
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
