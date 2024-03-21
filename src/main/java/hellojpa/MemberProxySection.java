package hellojpa;


import jakarta.persistence.*;

import java.time.LocalDateTime;

//@Entity
public class MemberProxySection extends BaseEntity {
//    @Id @GeneratedValue
//    @Column(name = "MEMBER_ID")
//    private Long id;
//
//    @Column(name = "USERNAME")
//    private String name;
////    //Member와 Team 중 하나만 조회한다면? 지연 로딩 셋팅
//    @ManyToOne(fetch =FetchType.LAZY ) // 지연 로딩 셋팅
//    @JoinColumn(name = "TEAM_ID")
//    private Team team;

    //Member와 Team을 같이 조회한다면? 즉시 로딩 셋팅 * 하지만실무에서는 즉시 로딩을 사용하면 안된다.
//    @ManyToOne(fetch =FetchType.EAGER ) // 즉시 로딩 셋팅
//    @JoinColumn(name = "TEAM_ID")
//    private Team team;

//    private String createdBy;
//    private LocalDateTime createdLocalDate;
//
//    private String modifiedBy;
//    private LocalDateTime modifiedLocalDate;
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//    public Team getTeam() {
//        return team;
//    }
//
//    public void setTeam(Team team) {
//        this.team = team;
//    }

}
