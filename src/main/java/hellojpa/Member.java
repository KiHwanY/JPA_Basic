package hellojpa;


import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Member {
    @Id @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "USERNAME")
    private String name;
    @Embedded
    private Address homeaddress;

    @ElementCollection // 기본 값이 Lazy 이다.
    @CollectionTable(name = "FAVORITE_FOOD" , joinColumns = @JoinColumn(name = "MEMBER_ID")) // 외래 키로 잡는다.
    @Column(name = "FOOD_NAME")
    private Set<String> favoriteFoods = new HashSet<>();

    // 순서 컬럼 추가해서 풀 수는 있다. 하지만 절대 사용하면 안된다.
    // 예시로 0,1,2,3 순서 데이터가 들어갔을 때 중간에 2번을 빼먹으면 2번이 null로 들어온다.
    //@OrderColumn(name = "address_history_order") // 순서 컬럼 추가해서 풀 수는 있다. 하지만 절대 사용하면 안된다.
    // 그래서 완전히 다르게 풀어야 한다.
    // 값 타입 컬렉션을 매핑하는 테이블은 모든 컬럼을 묶어서 기본 키를 구성해야 한다. : null 입력 x , 중복 저장 x
    // 실무에서는 상황에 따라 값 타입 컬렉션 대신에 일대다 관계를 고려
//    @ElementCollection
//    @CollectionTable(name = "ADDRESS" , joinColumns = @JoinColumn(name = "MEMBER_ID"))// 외래 키로 잡는다.
//    private List<Address> addressesHistory = new ArrayList<>();

    //값타입으로 매핑하는 게 아니라 Entity로 매핑한다.
    @OneToMany(cascade = CascadeType.ALL , orphanRemoval = true)
    @JoinColumn(name = "MEMBER_ID")
    private List<AddressEntity> addressesHistory = new ArrayList<>();


    //Period 기간

    // 주소
    @Embedded
    private Period period;

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

    public Period getPeriod() {
        return period;
    }

    public void setPeriod(Period period) {
        this.period = period;
    }

    public Address getHomeaddress() {
        return homeaddress;
    }

    public void setHomeaddress(Address homeaddress) {
        this.homeaddress = homeaddress;
    }

    public Set<String> getFavoriteFoods() {
        return favoriteFoods;
    }

    public void setFavoriteFoods(Set<String> favoriteFoods) {
        this.favoriteFoods = favoriteFoods;
    }

//    public List<Address> getAddressesHistory() {
//        return addressesHistory;
//    }
//
//    public void setAddressesHistory(List<Address> addressesHistory) {
//        this.addressesHistory = addressesHistory;
//    }


    public List<AddressEntity> getAddressesHistory() {
        return addressesHistory;
    }

    public void setAddressesHistory(List<AddressEntity> addressesHistory) {
        this.addressesHistory = addressesHistory;
    }
}
