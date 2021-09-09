package jpql;

import jpql.Domain.Member;
import jpql.Domain.Team;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class BulkMain {

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

            Member member = new Member();
            member.setUsername("회원1");

            member.addteam(team);
            em.persist(member);

            Member member2 = new Member();
            member2.setUsername("회원2");
            member2.addteam(team);
            em.persist(member2);

            Member member3 = new Member();
            member3.setUsername("회원3");
            member3.addteam(teamB);
            em.persist(member3);

            /**
             * 벌크 연산
             */

            /*em.flush();
            em.clear();

            int resultCount = em.createQuery("update Member m set m.age = 20").executeUpdate();

            System.out.println("resultCount = " + resultCount);

            List<Member> findMember = em.createQuery("select m from Member m", Member.class).getResultList();

            for (Member member1 : findMember) {
                System.out.println("member1 = " + member1);
            }*/

            /**
             * 주의할 점
             * 벌크 연산후 em.clear()를 호출한 후에 사용해야 데이터 정합성이 맞는다.
             */

            // createQuery 하는 시점에 flush()가 발생한다.
            int resultCount = em.createQuery("update Member m set m.age = 20").executeUpdate();

            System.out.println("resultCount = " + resultCount);

            em.clear();

            List<Member> findMember = em.createQuery("select m from Member m", Member.class).getResultList();

            for (Member member1 : findMember) {
                System.out.println("member1 = " + member1);
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
