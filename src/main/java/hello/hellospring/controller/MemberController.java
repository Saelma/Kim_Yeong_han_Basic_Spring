package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;


@Controller
public class MemberController {

    private final MemberService memberService;

    @Autowired // Autowired를 씀으로써(연결), 멤버 컨트롤러가 생성됐을 때 스프링 부트에 등록된 멤버 서비스의 객체를 가져다 쓴다
               // 이를 의존관계 주입이라 한다 Dependency Injection
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new") // GetMapping은 데이터를 조회하고자 할 때
    public String createForm(){
        return "members/createMemberForm";
    }

    @PostMapping("/members/new") // PostMapping은 데이터를 넣어서 post형식으로 제출할 때
    public String create(MemberForm form){
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/"; // 회원가입이 끝나면 home으로 보냄.
    }

    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members",members);
        return "members/memberList";

    }
}

/**
 * GetMapping -> createMemberForm -> PostMapping 순으로 동작
 */