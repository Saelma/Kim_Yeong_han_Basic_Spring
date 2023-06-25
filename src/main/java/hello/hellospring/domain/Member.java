package hello.hellospring.domain;

import javax.persistence.*;

@Entity // JPA가 관리하는 엔티티라 여김
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    // db가 Auto Generate를 통해 자동으로 생성된, 기계가 생성한 ID를 아이덴티티라 함
    private Long id;

    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
