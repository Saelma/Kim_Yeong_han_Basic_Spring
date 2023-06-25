package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

//@Repository
public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    /**
     *
     * Optional은 일 수도 있고, 아닐 수도 있는 즉, Null값이 잇는 것을 작성하기 위할 때
     */
    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream().filter(member -> member.getName().equals(name)).findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    /**
     * clearStore은 테스트를 작성 시 , 한 테스트가 다른 테스트에게 영향이 끼쳐지기 때문에
     * 테스트를 한 후, 값을 초기화하기 위함임.
     */
    public void clearStore(){
        store.clear();
    }
}

/**
 * Id값은 ++Sequence에 의해 자동으로 올라감.
 * Map은 리스트나 배열같은 순차적이 아닌, key를 통해 value를 얻는 형식임
 * ex( map.put("people", "사람");
 *     map.put("baseball", "야구");
 */