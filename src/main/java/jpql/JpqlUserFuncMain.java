package jpql;

import jpql.Domain.Member;
import jpql.Domain.MemberType;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.Date;
import java.util.List;

public class JpqlUserFuncMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            Member member1 = new Member();
            member1.setUsername("관리자1");
            em.persist(member1);

            em.flush();
            em.clear();

            // now_date() -> now()
            String query = "select function('now_date') from Member m";
            //String query = "select now_date() from Member m";
            List<Date> resultList = em.createQuery(query, Date.class).getResultList();

            for (Date s : resultList) {
                System.out.println("s = " + s);
            }

            // group_concat -> group_concat
            /*Member member2 = new Member();
            member2.setUsername("관리자2");
            em.persist(member2);

            String query = "select function('group_concat',m.username) from Member m";
            //String query = "select group_concat(m.username) from Member m";
            List<String> resultList = em.createQuery(query, String.class).getResultList();

            for (String s : resultList) {
                System.out.println("s = " + s);
            }*/

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
