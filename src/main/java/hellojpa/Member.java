package hellojpa;


import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Member extends BaseEntity {
    @Id @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "USERNAME")
    private String name;

    @ManyToOne // 멤버 입장에서는 N 이고 팀은 1이기 때문에
    @JoinColumn(name = "TEAM_ID") // join 시킬 컬럼 지정
    private Team team; // 단방향 연관관계 , 연관관계 주인이다.

    private String createdBy;
    private LocalDateTime createdLocalDate;

    private String modifiedBy;
    private LocalDateTime modifiedLocalDate;

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

}
