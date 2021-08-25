package jpql;

import jpql.Domain.Member;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class ParameterBindingMain {
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

            // 이름 기준으로 파라미터 바인딩
            List<Member> resultList = em.createQuery("select m from Member m where m.age >:age", Member.class)
                    .setParameter("age", 10)
                    .getResultList();

            for (Member mem : resultList) {
                System.out.println("mem.getUsername() = " + mem.getUsername());
            }

            // 순서 기준으로 파라미터 바인딩
            List<Member> resultList2 = em.createQuery("select m from Member m where m.age >?1", Member.class)
                    .setParameter(1, 10)
                    .getResultList();

            for (Member mem : resultList2) {
                System.out.println("mem2.getUsername() = " + mem.getUsername());
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
