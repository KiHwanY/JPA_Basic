package hellojpa;

import jakarta.persistence.*;
import org.hibernate.Hibernate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public class JpaMain {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin(); //  트랜잭션 시작
        try{
            Member member = new Member();
            member.setHomeaddress(new Address("homeCity" , "street" , "10000"));

            member.getFavoriteFoods().add("치킨");
            member.getFavoriteFoods().add("족발");
            member.getFavoriteFoods().add("피자");

//            member.getAddressesHistory().add(new Address("old1" , "street" , "10000"));
//            member.getAddressesHistory().add(new Address("old2" , "street" , "10000"));

            em.persist(member);

            em.flush();
            em.clear();
            // 값 타입 컬렉션도 지연 로딩 전략 사용한다.
            System.out.println("============== START ====================");
            Member findMember = em.find(Member.class , member.getId());

            // 값 타입 조회
//            List<Address> addressesHistory = findMember.getAddressesHistory();
//
//            for (Address address : addressesHistory) {
//                System.out.println("address = " + address.getCity());
//            }
//
//            Set<String> favoriteFoods = findMember.getFavoriteFoods();
//            for (String favoriteFood : favoriteFoods) {
//                System.out.println("favoriteFood = " + favoriteFood);
//            }
            // 값 타입 수정
            //homeCity -> NewCity
            //아래 방법 처럼 사용하면 사이드 이펙트가 발생한다.
//            findMember.getHomeaddress().setCity("newCity"); // 이 방법은 사용하면 안된다.
            Address old = findMember.getHomeaddress();
            findMember.setHomeaddress(new Address("newCity" ,old.getStreet(), old.getZipcode() )); // 새로운 인스턴스로 교체해야 한다.
            // 값 타입 안에 있는 필드 하나만 바꾼다? 값 타입은 추적이 안된다.

            //치킨 -> 한식
            //FavoriteFood 는 단순 String 이다.String은 통째로 갈아 껴야한다.
            // 업데이트라는 거 자체를 하면 안된다. 물론 할 수도 없다.
            findMember.getFavoriteFoods().remove("치킨");
            findMember.getFavoriteFoods().add("한식");

            findMember.getAddressesHistory().remove(new AddressEntity("old1" , "street" , "10000"));
            // 기존에 남아 있는 old2, newCity = insert 쿼리가 두 번 나간다.
            findMember.getAddressesHistory().add(new AddressEntity("newCity" , "street" , "10000"));
            // 값 타입 컬렉션은 영속성 전이(Cascade) + 고아 객체 제거 기능을 필수로 가진다고 볼 수 있다.


//            Address address = new Address("city" , "street","100");
//            Member member = new Member();
//            member.setName("hello");
//            member.setAddress(address);
//            member.setPeriod(new Period());
//            em.persist(member);
//
//            //불변 객체 설정
//            Address address1 = new Address(address.getStreet(),  address.getZipcode(), "NewCity");
//            member.setAddress(address1);

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

