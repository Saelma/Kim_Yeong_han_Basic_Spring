package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {

    private final MemberRepository memberRepository = new MemoryMemberRepository();

    /**
    회원가입
     */
    public long join(Member member){
        // 같은 이름이 있는 중복 회원X

        validateDuplicateMember(member); // 중복 회원 검증

        /**
         *         memberRepository.findByName(member.getName())
         *                 .ifPresent(m-> {
         *                     throw new IllegalStateException("이미 존재하는 회원입니다.");
         *                 });
         *         원래는 이런 내용이었으나,
         *         Ctrl + Alt + M으로, Extract Method를 통해 메서드를 추출한 방식이다.
         */

        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m-> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }
    /**
     * 원래는 이런 방식이었으나, Optional이 예쁘지 않다고 생각할 경우 위 방식도 가능
     *
     Optional<Member> result = memberRepository.findByName(member.getName());
     result.ifPresent(m-> {
     throw new IllegalStateException("이미 존재하는 회원입니다.");
     });
     */

    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    /**
     전체 회원 조회
     */
    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById((memberId));
    }
}

/**
 ifPresent란 예를 들어 m이라는 값이 있으면, throw new라는 로직이 동작하는 것이다.
 (Optional이라 가능)
 */