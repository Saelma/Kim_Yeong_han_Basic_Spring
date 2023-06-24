package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findById(Long id);
    Optional<Member> findByName(String name);
    List<Member> findAll();

}

/**
 * 인터페이스란, 다른 클래스를 작성할 때 기본이 되는 틀을 제공한다.
 * 쉽게 말하자면 객체의 사용방법을 가이드라인 하는 것이다.
 */