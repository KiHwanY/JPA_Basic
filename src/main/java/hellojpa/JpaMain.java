package hellojpa;

import jakarta.persistence.*;

import java.util.List;
/*
*
*
* */
public class JpaMain {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin(); //  트랜잭션 시작
        try{
            // 연관관계 적용 전
            // 객체를 테이블에 맞추어 모델링 (외래 키 식별자를 직접 다룸)
//            Team team = new Team();
//            team.setName("teamA");
//            em.persist(team);
//
//            Member member = new Member();
//            member.setName("member1");
//            member.setTeamId(team.getId());
//            em.persist(member);
//
//            //(식별자로 다시 조회, 객체 지향적인 방법은 아니다.)
//            // 조회
//            Member findMember = em.find(Member.class, member.getId());
//
//            Long findTeamId = findMember.getTeamId();
//
//            // 연관관계가 없음
//            Team findTeam = em.find(Team.class, findTeamId);
            /*
            *   [객체를 테이블에 맞추어 데이터 중심으로 모델링하면, 협력 관계를 만들 수 없다.]
            *
            *   - 테이블은 외래 키로 조인을 사용해서 연관된 테이블을 찾는다.
            *   - 객체는 참조를 사용해서 연관된 객체를 찾는다.
            *   - 테이블과 객체 사이에는 이런 큰 간격이 있다.
            * */
            // 객체 지향 모델링
            // (연관관계 저장)

            //저장
            Team team = new Team();
            team.setName("teamA");
            em.persist(team);

            Member member = new Member();
            member.setName("member1");
            member.setTeam(team);
            em.persist(member);

            //영속성 컨텍스트 제외 시키고 DB에서 가져오는 쿼리 확인하기
            em.flush(); // 영속성 컨텍스트에 있는 디비 쿼리 날려서 싱크 맞춤
            em.clear(); // 영속성 컨텍스트 클리어

            // 조회
            Member findMember = em.find(Member.class, member.getId());

            Team findTeam = findMember.getTeam();
            System.out.println("findTeam = " + findTeam.getName());

            // 수정
            Team newTeam = em.find(Team.class, 100L);
            findMember.setTeam(newTeam);

            tx.commit(); //  트랜잭션 커밋
        } catch (Exception e){
            tx.rollback();
        }finally {
            em.close();
        }
        emf.close();
    }
}

