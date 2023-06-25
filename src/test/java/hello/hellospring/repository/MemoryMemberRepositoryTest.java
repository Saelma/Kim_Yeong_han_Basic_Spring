package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach(){
        repository.clearStore();
    }
    @Test
    public void save(){
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();
        assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();

        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);

    }
}
/*
테스트 클래스를 먼저 작성하고 MemoryMemberRepository 클래스를 작성할 수 있다
별 모양 작품을 예로 들었을 때, 미리 검증할 수 있는 틀(테스트)를 만들고,
작품이 완성되면 꽂히는지 안 꽂히는지 해본다
즉, 테스트를 먼저 만들고 구현 클래스를 만들어 돌려보는 방식을 테스트 주도 방식, TDD라고 한다.
 */

/*
테스트를 없이 개발한다는 건, 혼자는 그럴 수 있으나
여러명이서 할 경우 코드 베이스가 몇만~몇십만이 넘어갈 경우
테스트가 없이는 거의 불가능하다.
 */
