package hellojpa;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
/*
*   [ 객체의 양방향 관계 ]
*   - 객체의 양방향 관계는 사실 양방향 관계가 아니라 서로 다른 단방향 관계 2개다.
*   - 객체를 양방향으로 참조하려면 단방향 연관관계를 2개 만들어야 한다.
*
*   [ 테이블의 양방향 연관관계 ]
*   - 테이블은 외래 키 하나로 두 테이블의 연관관계를 관리
*   - MEMBER.TEAM_ID 외래 키 하나로 양방향 연관관계 가짐(양쪽으로 조인할 수 있다.)
* */

@Entity
public class Team { // 주인이 아닌 쪽은 읽기만 가능하다.

    @Id @GeneratedValue
    @Column(name = "TEAM_ID")
    private Long id;

    private String name;

    // add 할 때 nullPoint 방지를 위한 ArrayList 초기화
    @OneToMany(mappedBy = "team") // 일대다 매핑해서 주인의 어떤 변수와 매핑되어야 하는지?
    private List<Member> members = new ArrayList<>();
    /*
    *   [ 객체와 테이블이 관계를 맺는 차이 ]
    *   - 객체 연관관계 = 2개
    *       -  회원 -> 팀 연관관계 1개(단방향)
    *       -  팀 -> 회원 연관관계 1개(단방향)
    *   - 테이블 연관관계 = 1개
    *       - 회원 <-> 팀의 연관관계 1개(양방향)
    * */
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

    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }
}
