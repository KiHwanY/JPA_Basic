package hellojpa;

import jakarta.persistence.*;

import java.util.List;
/*
*   양방향 연관 매핑을 할 때는 결론적으로 양쪽에다가 값을 다 세팅을 해주는게 맞다.
* */
/*
*   [ 양방향 매핑 정리 ]
*   - 단방향 매핑만으로도 이미 연관관계 매핑은 완료(첫 설계는 단방향 매핑만으로 이미 완료가 되어 있어야 한다.)
*   - 양방향 매핑은 반대 방향으로 조회(객체 그래프 탐색) 기능이 추가된 것 뿐
*   - JPQL에서 역방향으로 탐색할 일이 많음
*   - 단방향 매핑을 잘 하고 양방향은 필요할 때 추가해도 됨
*       (테이블에 영향을 주지 않음)
*
*   [ 연관관계의 주인을 정하는 기준 ]
*   - 비즈니스 로직을 기준으로 연관관계의 주인을 선택하면 안됨
*   - 연관관계의 주인은 외래 키의 위치를 기준으로 정해야 함
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
            member.changeTeam(team);
            em.persist(member);
            // 위 방식처럼 changeTeam 메서드에 넣어주는 Member 기준으로 하는 방식이 있고
            // 아래처럼 team에 addMember() 메서드를 생성해서 Team  기준으로 하는 방식이 있다
            // 방식은 자유다.
            //team.addMember(member);

            //역방향(주인이 아닌 방향)만 연관관계 설정 (Entity Set 설계할 때 넣어준다.)
//            team.getMembers().add(member); 양방향 매핑 시 가장 많이 하는 실수


            //영속성 컨텍스트 제외 시키고 DB에서 가져오는 쿼리 확인하기
            em.flush(); // 영속성 컨텍스트에 있는 디비 쿼리 날려서 싱크 맞춤
            em.clear(); // 영속성 컨텍스트 클리어
            // 플러시, 클리어를 안하면 위 코드의 데이터를 갖고 유지된 상태에서 중첩으로 들어간다.
            // 지연 로딩
//            Team findTeam = em.find(Team.class, team.getId()); // 1차 캐시
//            List<Member> members = findTeam.getMembers();
//
//            System.out.println("=====================================");
//            for (Member m : members) {
//                System.out.println("m = " + m.getName());
//            }
//            System.out.println("=====================================");


            // 조회
            Member findMember = em.find(Member.class, member.getId());

//            Team findTeam = findMember.getTeam();
//            System.out.println("findTeam = " + findTeam.getName());
//
//            // 수정
//            Team newTeam = em.find(Team.class, 100L);
//            findMember.setTeam(newTeam);

            List<Member> members = findMember.getTeam().getMembers();

            for (Member m : members) {
                System.out.println("m = " + m.getName());
            }

            tx.commit(); //  트랜잭션 커밋
        } catch (Exception e){
            tx.rollback();
        }finally {
            em.close();
        }
        emf.close();
    }
}

