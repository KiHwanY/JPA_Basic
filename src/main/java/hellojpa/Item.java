package hellojpa;

import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED) // Join 전략
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE) // SINGLE_TABLE 전략
//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS) // TABLE_PER_CLASS 전략 -> 권장하지 않는다.
@DiscriminatorColumn(name = "DIS_TYPE") // Default name = DTYPE ,
// SINGLE_TABLE 전략에서는 DiscriminatorColumn을 설정 안해도 자동으로 생성된다.
// TABLE_PER_CLASS 전략에서는 DiscriminatorColumn을 테이블 자체가 다르기 때문에 사용해도 의미가 없다.
public abstract class Item { // 추상 클래스화
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private int price;


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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
