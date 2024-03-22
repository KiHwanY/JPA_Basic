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

            Member member = new Member();
            member.setName("hello");
            member.setAddress(new Address("city" , "street","100"));
            member.setPeriod(new Period());

            em.persist(member);


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

