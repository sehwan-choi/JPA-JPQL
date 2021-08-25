package jpql;

import jpql.Domain.Member;
import jpql.Domain.Team;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JoinMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            Team team = new Team();
            team.setName("team");
            em.persist(team);

            Member member = new Member();
            member.setUsername("member1");
            member.setAge(10);
            member.changeTeam(team);
            em.persist(member);

            /**
             * Inner join
             */
            /*
            String Query = "select m from Member m inner join m.team t where t.name = :teamname";
            List<Member> resultList = em.createQuery(Query, Member.class)
                    .setParameter("teamname","team")
                    .getResultList();

            System.out.println("Inner Join ------------------");
            for (Member member1 : resultList) {
                System.out.println("Username = " + member1.getUsername() + " Team Name = " + member1.getTeam().getName());
            }
            System.out.println("Inner Join ------------------");
             */

            /**
             * Left outer join
             */

            String Query2 = "select m from Member m left outer join m.team t where t.name = :teamname";
            List<Member> resultList2 = em.createQuery(Query2, Member.class)
                    .setParameter("teamname","team")
                    .getResultList();

            System.out.println("Left Outer Join ------------------");
            for (Member member1 : resultList2) {
                System.out.println("Username = " + member1.getUsername() + " Team Name = " + member1.getTeam().getName());
            }
            System.out.println("Left Outer Join ------------------");


            /**
             * theta join
             */
            /*String Query3 = "select m from Member m, Team t where m.username = t.name";
            List<Member> resultList3 = em.createQuery(Query3, Member.class)
                    .getResultList();

            System.out.println("Theta Join ------------------");
            for (Member member1 : resultList3) {
                System.out.println("Username = " + member1.getUsername() + " Team Name = " + member1.getTeam().getName());
            }
            System.out.println("Theta Join ------------------");
            */

            /**
             * 조인 대상 필터링 ON
             */
            /*
            String Query = "select m from Member m left join m.team t on t.name =:teamname";
            List<Member> resultList = em.createQuery(Query, Member.class)
                    .setParameter("teamname","team")
                    .getResultList();

            System.out.println("Join On ------------------");
            for (Member member1 : resultList) {
                System.out.println("Username = " + member1.getUsername() + " Team Name = " + member1.getTeam().getName());
            }
            System.out.println("Join On ------------------");
             */

            /**
             * 연관관계 없는 엔티티 외부 조인
             */
            /*
            Team team2 = new Team();
            team.setName("teamA");
            em.persist(team);

            Member member2 = new Member();
            member2.setUsername("teamA");
            member2.setAge(10);
            member2.changeTeam(team);
            em.persist(member2);

            String Query = "select m from Member m left join Team t on t.name = m.username";
            List<Member> resultList = em.createQuery(Query, Member.class)
                    .getResultList();

            System.out.println("Join On ------------------");
            for (Member member1 : resultList) {
                System.out.println("Username = " + member1.getUsername() + " Team Name = " + member1.getTeam().getName());
            }
            System.out.println("Join On ------------------");
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
