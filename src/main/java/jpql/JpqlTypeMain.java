package jpql;

import jpql.Domain.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpqlTypeMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {

            /**
             * 문자 숫자 Boolean 타입 표현
             */
            /*

            Team team = new Team();
            team.setName("team");
            em.persist(team);

            Member member = new Member();
            member.setUsername("member1");
            member.setAge(10);
            member.changeTeam(team);
            em.persist(member);

            List<Object[]> resultList = em.createQuery("select m, 'HELLO', TRUE, 10D, 10L, 10F from Member m ").getResultList();

            Object[] objects = resultList.get(0);
            System.out.println("objects[0] = " + objects[0]);
            System.out.println("objects[1] = " + objects[1]);   // 'HELLO'
            System.out.println("objects[2] = " + objects[2]);   //  'true'
            System.out.println("objects[3] = " + objects[3]);   //  10.0(DOUBLE)
            System.out.println("objects[4] = " + objects[4]);   //  10
            System.out.println("objects[4] = " + objects[5]);   //  10.0(FLOAT)
             */


            /**
             * ENUM
             */
            /*
            Team team = new Team();
            team.setName("team");
            em.persist(team);

            Member member = new Member();
            member.setUsername("member1");
            member.setAge(10);
            member.setType(MemberType.ADMIN);
            member.changeTeam(team);
            em.persist(member);

            //List<Member> resultList = em.createQuery("select m from Member m where m.type = jpql.Domain.MemberType.ADMIN", Member.class).getResultList();
            List<Member> resultList = em.createQuery("select m from Member m where m.type = :memberType", Member.class)
                    .setParameter("memberType", MemberType.ADMIN)
                    .getResultList();
            for (Member member1 : resultList) {
                System.out.println("member1 = " + member1);
            }
             */


            /**
             * 엔티티 타입
             */
            /*
            Book book = new Book();
            book.setBook("BOOK");
            book.setName("BOOK_NAME");
            em.persist(book);

            Album album = new Album();
            album.setAlbum("ALBUM");
            album.setName("ALBUM_NAME");
            em.persist(album);

            Movie movie = new Movie();
            movie.setMovie("MOVIE");
            movie.setName("MOVIE_NAME");
            em.persist(movie);
            
            // 상속 관계에서 Book 타입만 조회할경우 TYPE(i) = Book, Album 타입만 조회할 경우 TYPE(i) = Album 으로 사용한다.
            List<Item> resultList = em.createQuery("select i from Item i where TYPE(i) = Book ", Item.class)
                    .getResultList();
            for (Item item : resultList) {
                System.out.println("item = " + item);
            }
             */

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }

        emf.close();
    }
}
