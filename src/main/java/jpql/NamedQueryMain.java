package jpql;

import jpql.Domain.Member;
import jpql.Domain.Team;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class NamedQueryMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {


            Team team = new Team();
            team.setName("TeamA");
            em.persist(team);

            Team teamB = new Team();
            teamB.setName("TeamB");
            em.persist(teamB);

            Member member1 = new Member();
            member1.setUsername("회원1");

            member1.addteam(team);
            em.persist(member1);

            Member member2 = new Member();
            member2.setUsername("회원2");
            member2.addteam(team);
            em.persist(member2);

            Member member3 = new Member();
            member3.setUsername("회원3");
            member3.addteam(teamB);
            em.persist(member3);

            em.flush();
            em.clear();

            /**
             * Member.findByUsername 사용
             */
            List<Member> resultList = em.createNamedQuery("Member.findByUsername", Member.class)
                    .setParameter("username", "회원1")
                    .getResultList();

            for (Member member : resultList) {
                System.out.println("member = " + member);
            }

            /**
             * Member.count 사용
             */
            Long singleResult = em.createNamedQuery("Member.count", Long.class)
                    .getSingleResult();

            System.out.println("singleResult = " + singleResult);

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
