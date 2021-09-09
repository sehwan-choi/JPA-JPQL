package jpql;

import jpql.Domain.Member;
import jpql.Domain.MemberType;
import jpql.Domain.Team;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.Collection;
import java.util.List;

public class JpqlPathExpressionMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            Member member = new Member();
            member.setUsername("관리자1");

            Team team = new Team();
            team.setName("Team");
            member.addteam(team);
            em.persist(team);
            em.persist(member);

            Member member2 = new Member();
            member2.setUsername("관리자2");
            member2.addteam(team);
            em.persist(member2);

            em.flush();
            em.clear();

            /**
             * 단일 값 연관 경로
             * 묵시적 내부 조인 발생, 탐색 가능
             */
            /*
            JPQL :
            select
                a.team
            from
                Member a

            SQL :
            select
                team1_.TEAM_ID as team_id1_4_,
                        team1_.name as name2_4_
            from
                Member member0_
            inner join
                Team team1_
                    on member0_.TEAM_ID=team1_.TEAM_ID
             */
            /*String query = "select m.team from Member m";

            List<Team> resultList = em.createQuery(query, Team.class).getResultList();
            for (Team team1 : resultList) {
                System.out.println("team1 = " + team1.getName());
            }*/


            /**
             * 컬렉션 값 연관 경로
             * 묵시적 내부 조인 발생, 탐색 불가
             */
            /*
            JPQL :
            select
                t.members
            from
                Team t

            SQL :
            select
                members1_.MEMBER_ID as member_i1_1_,
                members1_.age as age2_1_,
                members1_.TEAM_ID as team_id5_1_,
                members1_.type as type3_1_,
                members1_.username as username4_1_
            from
                Team team0_
            inner join
                Member members1_
                    on team0_.TEAM_ID=members1_.TEAM_ID
             */
            /*String query = "select t.members from Team t";

            List<Collection> resultList = em.createQuery(query, Collection.class).getResultList();
            System.out.println("resultList = " + resultList);*/

            /**
             * 컬렉션 값 연관 경로(명시적 내부 조인 사용)
             * 탐색 가능
             */
            /*
            JPQL :
            select
                m
            from
                Team t
            inner join
                t.members m

            SQL :
            select
                members1_.MEMBER_ID as member_i1_1_,
                members1_.age as age2_1_,
                members1_.TEAM_ID as team_id5_1_,
                members1_.type as type3_1_,
                members1_.username as username4_1_
            from
                Team team0_
            inner join
                Member members1_
                    on team0_.TEAM_ID=members1_.TEAM_ID
             */
            String query = "select m from Team t inner join t.members m";

            List<Member> resultList = em.createQuery(query, Member.class).getResultList();
            for (Member member1 : resultList) {
                System.out.println("member1.getUsername() = " + member1.getUsername());
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
