package hellojpa;


import jakarta.persistence.*;
/*
*   [1:1 관계]
*   - 일대일 관계는 그 반대도 일대일
*   - 주 테이블이나 대상 테이블 중에 외래 키 선택 가능
*       - 주 테이블에 외래 키
*       - 대상 테이블에 외래 키
*   - 외래 키에 데이터베이스 유니크(UNI) 제약조건 추가
* */
@Entity
public class Member1_1 {
    @Id @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "USERNAME")
    private String name;

    @ManyToOne // 멤버 입장에서는 N 이고 팀은 1이기 때문에
    @JoinColumn(name = "TEAM_ID", insertable = false, updatable = false) // join 시킬 컬럼 지정
    private Team team; // 단방향 연관관계 , 연관관계 주인이다.
    @OneToOne // 1:1 관계
    @JoinColumn(name = "LOCKER_ID")
    private Locker locker;
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
    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", team=" + team +
                '}';
    }
}
