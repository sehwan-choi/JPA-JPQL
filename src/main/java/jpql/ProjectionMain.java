package jpql;

import jpql.Domain.Member;
import jpql.Domain.MemberDTO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class ProjectionMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {

            for(int i = 0 ; i < 10 ; i++) {
                Member member = new Member();
                member.setUsername("member" + (i + 1));
                member.setAge(i + 1);
                em.persist(member);
            }

            // Query[] 타입으로 조회
            List<Object[]> resultList = em.createQuery("select m.username, m.age from Member as m").getResultList();
            Object[] result = resultList.get(0);
            System.out.println("result[0] = " + result[0]);
            System.out.println("result[1] = " + result[1]);

            // Query 타입으로 조회
            List resultList2 = em.createQuery("select m.username, m.age from Member as m").getResultList();
            Object o = resultList2.get(0);
            Object[] result2 = (Object[])o;
            System.out.println("result[0] = " + result2[0]);
            System.out.println("result[1] = " + result2[1]);

            // new 키워드로 조회
            List<MemberDTO> resultList3 = em.createQuery("select new jpql.Domain.MemberDTO(m.username, m.age) from Member as m", MemberDTO.class).getResultList();
            for (MemberDTO memberDTO : resultList3) {
                System.out.println("memberDTO.getUsername() = " + memberDTO.getUsername());
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
