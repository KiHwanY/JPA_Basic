package hellojpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

//public class JpaMainProxy {
//
//    public static void main(String[] args) {
//
//        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
//        EntityManager em = emf.createEntityManager();
//
//        EntityTransaction tx = em.getTransaction();
//        tx.begin(); //  트랜잭션 시작
//        try{
//
////            Member member = em.find(Member.class, 1L);
////            printMemberAndTeam(member);
////            printMember(member);
//            Member member1 = new Member();
//            member1.setName("member1");
//            em.persist(member1);
//
////            Member member2 = new Member();
////            member2.setName("member2");
////            em.persist(member2);
//
//
//            em.flush();
//            em.clear();
//
////            Member m1 = em.find(Member.class, member1.getId());
////            System.out.println("m1 = " + m1.getClass());
////
////            Member reference = em.getReference(Member.class, member1.getId());
////            System.out.println("reference = "+ reference.getClass());
////
////            System.out.println("a == a : "+(m1 == reference));// 항상 true
//
//            Member refMember = em.getReference(Member.class, member1.getId());
//            System.out.println("refMember = " + refMember.getClass()); // Proxy
//            refMember.getName(); // 강제 초기화
//            //getPersistenceUnitUtil().isLoaded() -> 프록시 인스턴스의 초기화 여부 확인
//            System.out.println("isLoaded = " + emf.getPersistenceUnitUtil().isLoaded(refMember)); // false
//
////            Hibernate.initialize(refMember); // 프록시 강제 초기화
//
////            em.detach(refMember); // 영속성에서 꺼내기 , 준영속 상태
////            em.clear();
////            em.close();
//
////            refMember.getName(); // 호출
//
//
//            //m1.getName(); // 프록시 초기화
//
////            Member findMember = em.find(Member.class, member1.getId());
////            System.out.println("findMember = "+ findMember.getClass());
////
////            System.out.println("refMember == findMember : "+(refMember == findMember));// 항상 true
//
////            Member m2 = em.getReference(Member.class, member2.getId());
//
////            logic(m1,m2);
//
//
////            Member findMember = em.find(Member.class, member.getId());
////            Member findMember = em.getReference(Member.class, member.getId());
////            System.out.println("before findMember = " + findMember.getClass());
////            System.out.println("findMember = " + findMember.getId());
////            System.out.println("findMember.username = " + findMember.getName());
////            System.out.println("after findMember = " + findMember.getClass());
//            tx.commit(); //  트랜잭션 커밋
//        } catch (Exception e){
//            tx.rollback();
//            e.printStackTrace();
//        }finally {
//            em.close();
//        }
//        emf.close();
//    }
//
////    private static void logic(Member m1, Member m2) {
//////        System.out.println("m1 == m2 = " + (m1.getClass() == m2.getClass()));
////        System.out.println("m1 == m2 = " + (m1 instanceof Member));
////        System.out.println("m1 == m2 = " + (m2 instanceof Member));
////    }
//
////    private static void printMember(Member member) {
////        System.out.println("member = " + member.getName());
////    }
////
////    private static void printMemberAndTeam(Member member) {
////        String username = member.getName();
////        System.out.println("name = " + username);
////        Team team = member.getTeam();
////        System.out.println("team = " + team);
////    }
//}
//
