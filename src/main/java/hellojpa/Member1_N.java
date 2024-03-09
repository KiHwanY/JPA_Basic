package hellojpa;


import jakarta.persistence.*;

@Entity
public class Member1_N extends Member {
    @Id @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "USERNAME")
    private String name;
    @ManyToOne // 일대다 양방향
    @JoinColumn(name = "TEAM_ID",insertable = false, updatable = false)// 읽기 기능만 허용함.
    private Team1_N team;

    /*
    *   [ 일대다 양방향 정리 ]
    *   - 이런 매핑은 공식적으로 존재 x
    *   - @JoinColumn(name = "TEAM_ID",insertable = false, updatable = false)
    *   - 읽기 전용 필드를 사용해서 양방향 처럼 사용하는 방법
    *   - 다대일 양방향을 사용하자.
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

}
