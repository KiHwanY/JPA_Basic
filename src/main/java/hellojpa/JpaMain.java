package hellojpa;

import jakarta.persistence.*;

import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        /*
        *   [주의]
        *
        *   -엔티티 매니저 팩토리는 하나만 생성해서 애플리케이션 전체에서 공유
        *
        *   - 엔티티 매니저는 쓰레드간에 공유 x (사용하고 버려야 한다.)
        *
        *   - JPA의 모든 데이터 변경은 트랜잭션 안에서 실행
        * */
        /*
        *   [JPQL]
        *
        *   가장 단순한 조회 방법
        *   - EntityManager.find()
        *   - 객체 그래프 탐색(a.getB().getC())
        *   ==================================
        *   1.JPA를 사용하면 엔티티 객체를 중심으로 개발
        *   2.문제는 검색 쿼리
        *   3.검색을 할 때도 테이블이 아닌 엔티티 객체를 대상으로 검색
        *   4.모든 DB 데이터를 객체로 변환해서 검색하는 것은 불가능
        *   5.애플리케이션이 필요한 데이터만 DB에서 불러오려면 결국 검색 조건이 포함된 SQL이 필요
        *
        *
        *   1.JPA는 SQL을 추상화한 JPQL이라는 객체 지향 쿼리 언어 제공
        *   2.SQL과 문법 유사, SELECT,FROM,WHERE,GROUP BY,HAVING,JOIN 지원
        *   3.JPQL은 엔티티 객체를 대상으로 쿼리
        *   4.SQL은 데이터베이스 테이블을 대상으로 쿼리
        *
        *   1.테이블이 아닌 객체를 대상으로 검색하는 객체 지향 쿼리
        *   2.SQL을 추상화해서 특정 데이터베이스 SQL에 의존X
        *   3.JPQL을 한마디로 정의하면 객체 지향 SQL
        * */

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin(); //  트랜잭션 시작
        try{
//            Member member = new Member();
//            member.setId(1L);
//            member.setName("HelloA");
//            Member findMember = em.find(Member.class, 1L);// 찾아온다
//            System.out.println("findMember.id = " + findMember.getId());
//            System.out.println("findMember.name = " + findMember.getName());
//            findMember.setName("HelloJPA");

//            [페이징 처리 하는 법]
//            List<Member> result = em.createQuery("select m from Member as m", Member.class)
//                    .setFirstResult(5)
//                    .setMaxResults(8)
//                    .getResultList();
//            for (Member member : result) {
//                System.out.println("Member.name = "  + member.getName());
//            }

            //객체를 생성한 상태(비영속)
//            Member member = new Member();
//            member.setId(101L);
//            member.setName("HelloJPA");

//            System.out.println("=== BEFORE ===");
//            [1차 캐시에 저장됨]
//            em.persist(member);
//            System.out.println("=== AFTER ===");
//            [1차 캐시에서 조회]
//            Member findMember1 = em.find(Member.class , 101L);
//            Member findMember2 = em.find(Member.class , 101L);
//            <1차 캐시로 반복 가능한 읽기(REPEATABLE READ) 등급의 트랜잭션 격리 수준을 데이터베이스가 아닌 애플리케이션 차원에서 제공>

//            System.out.println("result = " + (findMember1 == findMember2));

//            System.out.println("findMember.id = " + findMember.getId());
//            System.out.println("findMember.name = " + findMember.getName());


//            Member member1 = new Member(150L,"A");
//            Member member2 = new Member(160L,"B");

//            객체를 저장한 상태(영속)
//            em.persist(member1);
//            em.persist(member2);
//
//            회원 엔티티를 영속성 컨텍스트에서 분리, 준영속 상태
//            em.detach(member)

//            엔티티 수정
//            Member member = em.find(Member.class, 150L);
//            member.setName("zzzzz");
//


//            엔티티 삭제(객체를 삭제한 상태(삭제))
//            Member member = em.find(Member.class , 150L);
//            em.remove(member);

            System.out.println("====================================");

            tx.commit(); // 트랜잭션 커밋
        } catch (Exception e){
            tx.rollback();
        }finally {
            em.close();
        }
        emf.close();
    }
}
/*
*   [영속성 컨텍스트]
*   - JPA를 이해하는데 가장 중요한 용어
*   - "엔티티를 영구 저장하는 환경"이라는 뜻
*   - EntityManager.persist(entity);
*
*   [엔티티 매니저? 영속성 컨텍스트?]
*   - 영속성 컨텍스트는 논리적인 개념
*   - 눈에 보이지 않는다.
*   - 엔티티 매니저를 통해서 영속성 컨텍스트에 접근
*
*   [Entity의 생명 주기]
*   - 비영속(new/transient) : 영속성 컨텍스트와 전혀 관계가 없는 새로운 상태
*   - 영속(managed) : 영속성 컨텍스트에 관리되는 상태
*   - 준영속(detached) : 영속성 컨텍스트에 저장되었다가 분리된 상태
*   - 삭제(removed) : 삭제된 상태
*
*   [영속성 컨텍스트의 이점]
*   - 1차 캐시
*   - 동일성(identity) 보장
*   - 트랜잭션을 지원하는 쓰기 지연(transactional write-behind)
*   - 변경 감지(Dirty Checking)
*   - 지연 로딩(Lazy Loading)
*
*   [플러시]
*   - 영속성 컨텍스트의 변경 내용을 데이터베이스에 반영
*
*   [플러시 발생]
*   - 변경 감지
*   - 수정된 엔티티 쓰기 지연 SQL 저장소에 등록
*   - 쓰기 지연 SQL 저장소의 쿼리를 데이터베이스에 전송(등록,수정,삭제 쿼리)
*
*   [영속성 컨텍스트를 플러시하는 방법]
*   - em.flush() : 직접 호출
*   - 트랜잭션 커밋 : 플러시 자동 호출
*   - JPQL 쿼리 실행 - 플러시 자동 호출
*
*   [JPQL 쿼리 실행 시 플러시가 자동으로 호출되는 이유]
*
    * em.persist(memberA);
     em.persist(memberB);
     em.persist(memberC);
     //중간에 JPQL 실행
    query = em.createQuery("select m from Member m", Member.class);
     List<Member> members= query.getResultList();

*   [플러시 모드 옵션]
*   em.setFlushMode(FlushModeType.COMMIT)
*
*   - FlushModeType.AUTO - > 커밋이나 쿼리를 실행할 때 플러시(기본값) -> 이걸 사용해라
*   - FlushModeType.COMMIT : 커밋할 때만 플러시
*
*   [ 플러시 정리]
*   - 영속성 컨텍스트를 비우지 않음
*   - 영속성 컨텍스트의 변경 내용을 데이터베이스에 동기화
*   - 트랜잭션이라는 작업 단위가 중요 -> 커밋 직전에만 동기화 하면 됨
* */
