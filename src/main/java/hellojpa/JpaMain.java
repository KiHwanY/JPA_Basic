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

            List<Member> result = em.createQuery("select m from Member as m", Member.class)
                    .setFirstResult(5)
                    .setMaxResults(8)
                    .getResultList();

            for (Member member : result) {
                System.out.println("Member.name = "  + member.getName());
            }

            tx.commit(); // 트랜잭션 커밋
        } catch (Exception e){
            tx.rollback();
        }finally {
            em.close();
        }
        emf.close();
    }
}
