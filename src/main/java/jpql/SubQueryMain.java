package jpql;

import jpql.Domain.Member;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class SubQueryMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {

            for(int i = 0 ; i < 100 ; i++) {
                Member member = new Member();
                member.setUsername("member" + (i + 1));
                member.setAge(i + 1);
                em.persist(member);
            }

            List<Member> resultList = em.createQuery("select m from Member m where m.age > (select avg(m2.age) from Member m2)", Member.class)
                    .getResultList();

            for (Member member : resultList) {
                System.out.println("member.getUsername() = " + member.getUsername());
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
