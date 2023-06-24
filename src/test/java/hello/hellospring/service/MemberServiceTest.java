package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Optional;


class MemberServiceTest {


    MemberService memberService;
    MemoryMemberRepository memberRepository;

    /**
     * 테스트가 실행되기 전 실행함
     */
    @BeforeEach
    public void beforeEach(){
         memberRepository = new MemoryMemberRepository();
         memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach(){
        memberRepository.clearStore();
    }

    @Test
    void 회원가입() {
        //given
        Member member = new Member();
        member.setName("hello");

        //when
        long saveId = memberService.join(member);

        //then
        Member findMember = memberService.findOne(saveId).get();
        Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외(){
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);
        /**
         * memberService.join(member2) 가 실행하면, Illegal 구문이 실행됨
         */
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        /**
         * 메세지 검증 방법
         */
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");


        //        try{
//            memberService.join(member2);
//        }catch (IllegalStateException e){
//            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//        }

        //then
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}

/**
 * 테스트 하고자 하는 파일을 Ctrl + Shift + T로
 * 테스트 파일에 패키지와 파일, 파일 제목등을 자동으로 생성해준다.
 */

/**
 * given, when, then으로 테스트 코드가 길어질 때,
 * given : 테스트 값이 주어지면
 * when : 이런 동작을 하고
 * then : 이런 결과가 나와야 한다.
 */