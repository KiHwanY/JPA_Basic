package hellojpa;

import jakarta.persistence.*;
import org.hibernate.Hibernate;

import java.time.LocalDateTime;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin(); //  트랜잭션 시작
        try{
//            Team teamA = new Team();
//            teamA.setName("teamA");
//            em.persist(teamA);
//
//            Team teamB = new Team();
//            teamB.setName("teamA");
//            em.persist(teamB);
//
//            Member member1 = new Member();
//            member1.setName("member1");
//            member1.setTeam(teamA);
//            em.persist(member1);
//
//            Member member2 = new Member();
//            member2.setName("member2");
//            member2.setTeam(teamB);
//            em.persist(member2);
//
//            em.flush();
//            em.clear();
//
//            Member m = em.find(Member.class , member1.getId());
//
//            List<Member> members = em.createQuery("select m from Member m",Member.class).getResultList();

//            System.out.println("m = " + m.getTeam().getClass());
//
//            System.out.println("========================");
//            System.out.println("teamName = " + m.getTeam().getName());
//            m.getTeam().getName(); // getName을 호출하는 순간 초기화
//            System.out.println("========================");
            Child child1 = new Child();
            Child child2 = new Child();

            Parent parent = new Parent();

            parent.addChild(child1);
            parent.addChild(child2);

            em.persist(parent);
//            em.persist(child1);
//            em.persist(child2);
            em.flush();
            em.clear();

            Parent findParent = em.find(Parent.class, parent.getName());
            findParent.getChildList().remove(0L);
            




            tx.commit(); //  트랜잭션 커밋
        } catch (Exception e){
            tx.rollback();
            e.printStackTrace();
        }finally {
            em.close();
        }
        emf.close();
    }

//    private static void logic(Member m1, Member m2) {
////        System.out.println("m1 == m2 = " + (m1.getClass() == m2.getClass()));
//        System.out.println("m1 == m2 = " + (m1 instanceof Member));
//        System.out.println("m1 == m2 = " + (m2 instanceof Member));
//    }

//    private static void printMember(Member member) {
//        System.out.println("member = " + member.getName());
//    }
//
//    private static void printMemberAndTeam(Member member) {
//        String username = member.getName();
//        System.out.println("name = " + username);
//        Team team = member.getTeam();
//        System.out.println("team = " + team);
//    }
}

