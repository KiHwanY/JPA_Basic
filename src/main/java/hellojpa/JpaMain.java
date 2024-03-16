package hellojpa;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin(); //  트랜잭션 시작
        try{

//            Movie movie = new Movie();
//            movie.setDirector("aaaaa");
//            movie.setActor("bbbb");
//            movie.setName("바람과함께사라지다.");
//            movie.setPrice(10000);
            Member member = new Member();
            member.setName("user1");
            member.setCreatedBy("kim");
            member.setCreatedLocalDate(LocalDateTime.now());

            em.persist(member);

            em.flush();
            em.clear();

//            Movie findMovie = em.find(Movie.class, movie.getId());
//            System.out.println("findMovie = "  + findMovie);

            tx.commit(); //  트랜잭션 커밋
        } catch (Exception e){
            tx.rollback();
        }finally {
            em.close();
        }
        emf.close();
    }
}

