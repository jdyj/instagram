package com.example.instagram.api;

import com.example.instagram.service.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;

@RestController
@RequiredArgsConstructor
public class TestController {

    private final TestService testService;


    /**
     * 카카오 Access_Token을 그대로 쓰는건 보안 상 문제가 생길수 있으므로 다음에 할 때 나만의 토큰을 만들어보기
     *
     */
    @RequestMapping("/kakaologin")
    public String home(@RequestParam(value = "code", required = false) String code) throws Exception {

        System.out.println("###################################");
        System.out.println("코드 : " + code);
        String access_Token = testService.getAccessToken(code);
        HashMap<String, Object> userInfo = testService.getUserInfo(access_Token);
        System.out.println(access_Token);

//        System.out.println("###email###" + userInfo.get("email"));
        System.out.println("###nickname###" + userInfo.get("nickname"));
//        System.out.println("###birthday###" + userInfo.get("birthday"));
//        System.out.println("###gender###" + userInfo.get("gender"));
        System.out.println("###################################");
        return "코드 : " + code + " 토큰 : " + access_Token;
    }
}
