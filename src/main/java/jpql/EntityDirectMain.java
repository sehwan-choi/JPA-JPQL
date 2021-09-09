package jpql;

import jpql.Domain.Member;
import jpql.Domain.Team;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class EntityDirectMain {

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
             * 엔티티 직접 사용 - 기본 키 값
             */
            /*String query = "select count(m) from Member m";
            //String query = "select count(m.id) from Member m"; // 위와 같은 SQL 쿼리가 실행된다.
            Long singleResult = em.createQuery(query, Long.class).getSingleResult();

            System.out.println("singleResult = " + singleResult);*/

            /**
             * 엔티티 직접 사용 - 기본 키 값
             * 엔티티를 파라미터로 전달
             */
            /*String query = "select m from Member m where m = :member";
            Member findMember = em.createQuery(query, Member.class).setParameter("member", member2).getSingleResult();

            System.out.println("findMember = " + findMember);*/

            /**
             * 엔티티 직접 사용 - 기본 키 값
             * 식별자를 직접 전달
             */
            /*String query = "select m from Member m where m.id = :memberid";
            Member findMember = em.createQuery(query, Member.class).setParameter("memberid", member2.getId()).getSingleResult();

            System.out.println("findMember = " + findMember);*/

            /**
             * 엔티티 직접 사용 - 외래 키 값
             * 엔티티를 파라미터로 전달
             */
            /*String query = "select m from Member m where m.team = :team";
            List<Member> resultList = em.createQuery(query, Member.class).setParameter("team",team).getResultList();

            for (Member member : resultList) {
                System.out.println("member = " + member);
            }*/

            /**
             * 엔티티 직접 사용 - 외래 키 값
             * 식별자를 직접 전달
             */
            String query = "select m from Member m where m.team.id = :teamid";
            List<Member> teamid = em.createQuery(query, Member.class).setParameter("teamid", team.getId()).getResultList();

            for (Member member : teamid) {
                System.out.println("member = " + member);
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
