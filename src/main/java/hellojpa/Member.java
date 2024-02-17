package hellojpa;


import jakarta.persistence.*;
/*
*   [@Entity]
*   -@Entity가 붙은 클래스는 JPA가 관리. Entity라 한다.
*   - JPA를 사용해서 테이블과 매핑할 클래스 @Entity 필수
*
*   <주의>
    - 기본 생성자 필수(파라미터가 없는 Public 또는 Protected 생성자)
    - final 클래스 , enum, interface, inner 클래스 사용 x
    - 저장할 필드에 final 사용 x

*   [@Entity 속성 정리]
*   - 속성 : name
*       -> JPA에서 사용할 엔티티 이름을 지정한다.
*       -> 기본값 : 클래스 이름을 그대로 사용(예 : Member)
*       -> 같은 클래스 이름이 없으면 가급적 기본값을 사용한다.
*
*   [@Table]
*   - @Table은 Entity와 매핑할 테이블 지정
*       - name : 매핑할 테이블 이름 -> 엔티티 이름을 사용
*       - catalog : 데이터베이스 catalog 매핑
*       - schema : 데이터베이스 schema 매핑
*       - uniqueConstraints(DDL) : DDL 생성 시에 유니크 제약 조건 생성
* */

@Entity //JPA가 관리할 객체
//@Table(name = "MBR")
//유니크 제약조건 추가
//DDL 생성 기능은 DDL을 자동 생성할 때만 사용되고 JPA의 실행 로직에는 영향을 주지 않는다.
//@Table(uniqueConstraints = {@UniqueConstraint(name = "NAME_AGE_UNIQUE", columnNames = {"NAME" , "AGE"})})
public class Member {
    @Id // 데이터베이스 PK와 매핑
    private Long id;

    //DDL 생성 기능
    //제약조건 추가 : 회원 이름은 필수, 10자 초과 x
    @Column(nullable = false, length = 10)
    private String name;

    public Long getId() {
        return id;
    }

    public Member(){
    }

    public Member(Long id, String name) {
        this.id = id;
        this.name = name;
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

/*
*   [Entity Mapping 소개]
*   - 객체와 테이블 매핑 : @Entity , @Table
*   - 필드와 컬럼 매핑 : @Column
*   - 기본 키 매핑 : @Id
*   - 연관 관계 매핑 : @ManyToOne , @JoinColumn
*
* */
