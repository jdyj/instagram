인스타그램 클론 코딩 BACK END
=============================

#### with FE 0균씨 https://github.com/klmyoungyun

### 기술 스택 SPRING BOOT, JPA, H2

## 공부 목표 랄까
```
MVC 패턴 이해하기
api 익숙해지기
효율적으로 열심히 짜보기
쿠키 사용해보기 @RequestHeader("cookie") String cookie
```

#### 21.03.02
```
처음 설계할때 게시판에서 댓글다는거를 그냥 String context로 받았는데
문득 생각해보니 그러면 댓글을 하나밖에 못씀 ^^; 
그래서 따로 Context라는 객체를 만들어서 연관관계 매핑을 시켜줌
이게 맞는지는 잘 모르겠음 ㅎㅎ

이러고 생각을 해보니 A라는 사람의 게시판에 B가 댓글을 달 상황이면
처음에 Context에 Member 연관관계를 매칭을 시켜주면 되는건가? 단방향으로 누가 썼는지만 알수있도록
어렵당

일단 다 때려치우고 천천히 로그인부터 하자
```

#### 21.03.03
```
선배님께서 알려주신 @RequestHeader("cookie") String cookie 이거를 사용해서 쿠키 구현을 해보려는데
자꾸 status 400 뜨면서 Bad Request라고 뜬다.. 
찾아보니 RequestHeader랑 ResponseBody랑 같이 사용할수가 없다고 한다.
RequestHeader ResponseBody등 좀 더 공부해야겠다.
```
