package hellojpa;

import jakarta.persistence.MappedSuperclass;

import java.time.LocalDateTime;

@MappedSuperclass // 맵핑 정보만 받는 슈퍼 클래스
public abstract class BaseEntity { // 추상 클래스
    /*
    *  [@MappedSuperclass]
    *   - 상속관계 매핑 X
    *   - 엔티티 X , 테이블과 매핑 X
    *   - 부모 클래스를 상속 받는 자식 클래스에 매핑 정보만 제공
    *   - 조회, 검색 불가(em.find(BaseEntity) 불가)
    *   - 직접 생성해서 사용할 일이 없으므로 추상 클래스 권장
    *
    *   - 테이블과 관계 없고, 단순히 엔티티가 공통으로 사용하는 매핑 정보를 모으는 역할
    *   - 주로 등록일, 수정일, 등록자, 수정자 같은 전체 엔티티에서 공통으로 적용하는 정보를 모을 때 사용
    *   - 참고: @Entity 클래스는 엔티티나 @MappedSuperclass 로 지정한 클래스만 상속 가능
    * */

    private String createdBy;
    private LocalDateTime createdLocalDate;

    private String modifiedBy;
    private LocalDateTime modifiedLocalDate;

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getCreatedLocalDate() {
        return createdLocalDate;
    }

    public void setCreatedLocalDate(LocalDateTime createdLocalDate) {
        this.createdLocalDate = createdLocalDate;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public LocalDateTime getModifiedLocalDate() {
        return modifiedLocalDate;
    }

    public void setModifiedLocalDate(LocalDateTime modifiedLocalDate) {
        this.modifiedLocalDate = modifiedLocalDate;
    }
}
