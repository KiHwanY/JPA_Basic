package hellojpa;


import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
/*
*   [요구 사항 추가]
*   - 회원은 일반 회원과 관리자로 구분해야 한다.
*   - 회원 가입일과 수정일이 있어야 한다.
*   - 회원을 설명할 수 있는 필드가 있어야 한다.
*   - 이 필드는 길이 제한이 없다.
* */
/*
*   [@Column]
*
*   name = 필드와 매핑할 테이블의 컬럼 이름 -> 객체의 필드 이름
*   insertable,updatable = 등록, 변경 가능 여부 -> TRUE
*   nullable(DDL) = null 값의 허용 여부를 설정한다. false로 설정하면 DDL 생성 시에 not null 제약조건이 붙는다.
*   unique(DDL) = @Table의 uniqueConstraints와 같지만 한 컬럼에 간단히 유니크 제약조건을 걸 때 사용한다.
*   columnDefinition(DDL) = 데이터베이스 컬럼 정보를 직접 줄 수 있다.
*       -> ex) varchar(100) default 'EMPTY' -> 필드의 자바 타입과 방언 정보를 사용함.
*   length(DDL) = 문자 길이 제약조건, String 타입에만 사용한다. -> 255
*
*   precision,scale(DDL) = BigDecimal 타입에서 사용한다.(BigInteger도 사용할 수 있다.).
*                          precision은 소수점을 포함한 전체 자릿수를, scale은 소수의 자릿수다.
*                          참고로 double, float 타입에는 적용되지 않는다.
*                          아주 큰 숫자나 정밀한 소수를 다루어야 할 때만 사용한다.
*                   -> precision = 19, scale =2
*
* */

//@Entity //JPA가 관리할 객체
public class MemberColumn {

    @Id
    private Long id;
    @Column(name = "name") // 컬럼 매핑
    private String username;
    private Integer age;
    @Enumerated(EnumType.STRING) // enum 타입 매핑
    private RoleType roleType;
    /*
    *   [@Enumerated]
    *   자바 enum 타입을 매핑할 때 사용한다.
    *   주의 사항으로 ORDINAL 사용 x
    *
    *   value = EnumType.ORDINAL : enum 순서를 데이터베이스에 저장
    *           EnumType.STRING : enum 이름을 데이터베이스에 저장
    *           -> EnumType.ORDINAL (기본값)
    *
    * */
    @Temporal(TemporalType.TIMESTAMP) // 날짜 타입 매핑
    private Date createDate;


    @Temporal(TemporalType.TIMESTAMP) // 날짜 타입 매핑
    private Date lastModifiedDate;

    private LocalDate testLocalDate;
    private LocalDateTime testLocalDateTime;

    /*
    *   [@Temporal]
    *   날짜 타입을 매핑할 때 사용
    *   (지금은 사실 필요 없다.)
    *   참고로 LocalDate, LocalDateTime을 사용할 때는 생략 가능(최신 하이버네이트 지원)
    *
    * • TemporalType.DATE: 날짜, 데이터베이스 date 타입과 매핑
        (예: 2013–10–11)
        • TemporalType.TIME: 시간, 데이터베이스 time 타입과 매핑
        (예: 11:11:11)
        • TemporalType.TIMESTAMP: 날짜와 시간, 데이터베이 스
        timestamp 타입과 매핑(예: 2013–10–11 11:11:11)
    * */
    @Lob // BLOB, CLOB 매핑
    private String description;
    /*  [@Lob]
    *   @Lob에는 지정할 수 있는 속성이 없다.
    *   매핑하는 필드 타입이 문자면 CLOB 매핑, 나머지는 BLOB 매핑
    *
    *   CLOB : String , char[], java.sql.CLOB
    *   BLOB : byte[], java.sql.BLOB
    * */

//    @Transient : 특정 필드를 컬럼에 매핑하지 않음(매핑 무시)
//                 필드 매핑 x , 데이터베이스에 저장x, 조회 x
//                 주로 메모리상에서만 임시로 어떤 값을 보관하고 싶을 때 사용

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public RoleType getRoleType() {
        return roleType;
    }

    public void setRoleType(RoleType roleType) {
        this.roleType = roleType;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
