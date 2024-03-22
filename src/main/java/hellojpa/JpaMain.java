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
            Address address = new Address("city" , "street","100");
            Member member = new Member();
            member.setName("hello");
            member.setAddress(address);
            member.setPeriod(new Period());
            em.persist(member);

            //불변 객체 설정
            Address address1 = new Address(address.getStreet(),  address.getZipcode(), "NewCity");
            member.setAddress(address1);

            //값 타입은 불변 객체로 만들어야 한다.

            //값 타입에서 첫번째 인덱스 값을 바꾸려면 복사를 해서 사용해야 한다.
//            Address copyAddress = new Address(address.getCity(),address.getCity(), address.getZipcode());
//
//            Member member2 = new Member();
//            member2.setName("hello2");
//            member2.setAddress(copyAddress);
//            member2.setPeriod(new Period());
//            em.persist(member2);
//
//            //절대 못잡는 버그. 업데이트 쿼리가 두개 나간다.
//            member.getAddress().setCity("newCity");


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

