package hellojpa;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Team { // 주인이 아닌 쪽은 읽기만 가능하다.

    @Id @GeneratedValue
    @Column(name = "TEAM_ID")
    private Long id;

    private String name;

//    @OneToMany(mappedBy = "team")
//    private List<Member> members = new ArrayList<>();

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
