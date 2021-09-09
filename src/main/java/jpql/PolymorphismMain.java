package jpql;

import jpql.Domain.Album;
import jpql.Domain.Book;
import jpql.Domain.Item;
import jpql.Domain.Movie;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class PolymorphismMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            
            Book book = new Book();
            book.setName("this is book");
            book.setBook("book");
            em.persist(book);

            Movie movie = new Movie();
            movie.setName("this is movie");
            movie.setMovie("movie");
            em.persist(movie);

            Album album = new Album();
            album.setName("this is movie");
            album.setAlbum("album");
            em.persist(album);
            
            em.flush();
            em.clear();

            /**
             * TYPE
             */
            /*String query = "select i from Item i where type(i) IN (Book, Movie)";
            List<Item> resultList = em.createQuery(query, Item.class).getResultList();

            for (Item item : resultList) {
                System.out.println("item.getName() = " + item.getName());
            }*/

            /**
             * TREAT
             */
            String query = "select i from Item i where treat(i as Book).book = 'book'";
            List<Item> resultList = em.createQuery(query, Item.class).getResultList();

            for (Item item : resultList) {
                System.out.println("item.getName() = " + item.getName());
            }

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
