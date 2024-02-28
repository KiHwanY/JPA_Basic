package hellojpa;


import jakarta.persistence.*;


/*
 *   [ 목표 ]
 *   - 객체와 테이블 연관 관계의 차이를 이해
 *   - 객체의 참조와 테이블의 외래 키를 매핑
 *
 *   - 용어 이해
 *       -> 방향(Direction) : 단방향, 양방향
 *       -> 다중성(Multiplicity) : 다대일(N:1) , 일대다(1:N), 일대일(1:1), 다대다(N:M) 이해
 *       -> 연관관계의 주인(Owner) : 객체 양방향 연관관계는 관리 주인이 필요 (중요한 용어다.)
 * */
/*
*   [ 예제 시나리오 ]
*   - 회원과 팀이 있다.
*   - 회원은 하나의 팀에만 소속될 수 있다.
*   - 회원과 팀은 다대일 관계다.
*
*   [ 연관관계의 주인(Owner) ]
*   -양방향 매핑 규칙
*       - 객체의 두 관계중 하나를 연관관계의 주인으로 지정
*       - 연관관계의 주인만이 외래 키를 관리(등록,수정)
*       - 주인이 아닌쪽은 읽기만 가능
*       - 주인은 mappedBy 속성 사용 x
*       - 주인이 아니면 mappedBy 속성으로 주인 지정
*
*   [ 누구를 주인으로? ]
*   - 외래 키가 있는 곳을 주인으로 정해라.
*   - 여기서 Member.team이 연관관계의 주인이다.
* */
@Entity
public class Member {
    @Id @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "USERNAME")
    private String name;

//    @Column(name = "TEAM_ID")
//    private Long teamId;

    @ManyToOne // 멤버 입장에서는 N 이고 팀은 1이기 때문에
    @JoinColumn(name = "TEAM_ID") // join 시킬 컬럼 지정
    private Team team; // 단방향 연관관계



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

//    public Long getTeamId() {
//        return teamId;
//    }
//
//    public void setTeamId(Long teamId) {
//        this.teamId = teamId;
//    }
}
