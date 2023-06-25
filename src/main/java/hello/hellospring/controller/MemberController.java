package hello.hellospring.controller;

import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;


@Controller
public class MemberController {

    private final MemberService memberService;

    @Autowired // Autowired를 씀으로써(연결), 멤버 컨트롤러가 생성됐을 때 스프링 부트에 등록된 멤버 서비스의 객체를 가져다 쓴다
               // 이를 의존관계 주입이라 한다 Dependency Injection
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
}
