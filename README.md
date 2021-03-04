인스타그램 클론 코딩 BACK END
=============================

#### with FE 0균씨 https://github.com/klmyoungyun

### 기술 스택 SPRING BOOT, JPA

## 공부 목표 랄까
```
MVC 패턴 이해하기
api 익숙해지기
효율적으로 열심히 짜보기
쿠키 사용해보기 @RequestHeader("cookie") String cookie
```

#### 21.03.02

처음 설계할때 게시판에서 댓글다는거를 그냥 String context로 받았는데
문득 생각해보니 그러면 댓글을 하나밖에 못씀 ^^; 
그래서 따로 Context라는 객체를 만들어서 연관관계 매핑을 시켜줌
이게 맞는지는 잘 모르겠음 ㅎㅎ

이러고 생각을 해보니 A라는 사람의 게시판에 B가 댓글을 달 상황이면
처음에 Context에 Member 연관관계를 매칭을 시켜주면 되는건가? 단방향으로 누가 썼는지만 알수있도록
어렵당

일단 다 때려치우고 천천히 로그인부터 하자


#### 21.03.03

선배님께서 알려주신 @RequestHeader("cookie") String cookie 이거를 사용해서 쿠키 구현을 해보려는데
자꾸 status 400 뜨면서 Bad Request라고 뜬다.. 
찾아보니 RequestHeader랑 ResponseBody랑 같이 사용할수가 없다고 한다.
RequestHeader ResponseBody등 좀 더 공부해야겠다.


```java
    //로그인 (쿠키를 생성한 뒤 httpResponse에 담아서 보내준 코드) (!!코드리뷰 시급!!)
    @PostMapping("/signIn/v1/member")
    public SignInMemberResponse memberSignInV1(@CookieValue(value = "cookie", defaultValue = "defaultcookie") String cookie,
                                               HttpServletResponse httpResponse,
                                               @RequestBody @Valid SignInMemberRequest request) {
        Member member = memberService.signIn(request.getEmail(), request.getPassword());
        System.out.println("cookie = " + cookie);
        Cookie myCookie = new Cookie("cookie", cookie);
        myCookie.setMaxAge(60*60*24);
        myCookie.setPath("/");

        httpResponse.addCookie(myCookie);
        System.out.println("myCookie = " + myCookie);
        return new SignInMemberResponse(member.getId());
    }
```

#### 21.03.04

Member에 있는 Board를 다 출력하는 api를 만들고 있었는데 Member와 Board에 양방향 연관관계가 설정이 되어있어서 무한루프가 돎..
는 바로 고침 ㅋㅎㅋㅎ
```java
    @GetMapping("/myPage/v1/member/{id}/boards")
    public ShowMyBoardResponse myPageBoard(@PathVariable("id") Long memberId) {
        Member member = memberService.findOne(memberId);
        List<Board> findBoards = member.getBoards();
        List<BoardDto> collect = findBoards.stream()
                .map(board -> new BoardDto(board.getDescription(), board.getHeartCount()))
                .collect(Collectors.toList());

        return new ShowMyBoardResponse(collect);
    }
```
