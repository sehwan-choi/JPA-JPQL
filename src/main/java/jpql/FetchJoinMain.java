package jpql;

import jpql.Domain.Member;
import jpql.Domain.Team;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class FetchJoinMain {

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

            em.flush();
            em.clear();

            /**
             * 엔티티 Fetch Join
             */
            /*String query = "select m from Member m join fetch m.team";
            List<Member> resultList = em.createQuery(query, Member.class).getResultList();

            for (Member member1 : resultList) {
                System.out.println("username = " + member1.getUsername() + " # teamname = " + member1.getTeam().getName());
            }*/

            /**
             * 컬렉션 Fetch Join(중복 제거 전)
             */
            /*String query = "select t from Team t join fetch t.members";
            List<Team> resultList = em.createQuery(query, Team.class).getResultList();
            System.out.println("size = " + resultList.size());

            for (Team team1 : resultList) {
                System.out.println("teamname = " + team1.getName());
                for(Member mem : team1.getMembers())
                {
                    System.out.println("    memberUsername = " + mem.getUsername());
                }
            }*/

            /**
             * 컬렉션 Fetch Join(중복 제거 후)
             */
            /*String query = "select distinct t from Team t join fetch t.members";
            List<Team> resultList = em.createQuery(query, Team.class).getResultList();
            System.out.println("size = " + resultList.size());

            for (Team team1 : resultList) {
                System.out.println("teamname = " + team1.getName());
                for(Member mem : team1.getMembers())
                {
                    System.out.println("    memberUsername = " + mem.getUsername());
                }
            }*/

            /**
             * 컬렉션 Fetch Join 페이징 사용(위험)
             * 일대다인 경우
             * 위험한 이유 : 쿼리문을 보면 페이징 쿼리가 나가지않음, 모든 데이터를 가져오게됨
             */
            String query = "select t from Team t join fetch t.members";
            List<Team> resultList = em.createQuery(query, Team.class)
                    .setFirstResult(0)
                    .setMaxResults(1)
                    .getResultList();
            System.out.println("size = " + resultList.size());

            for (Team team1 : resultList) {
                System.out.println("teamname = " + team1.getName());
                for(Member mem : team1.getMembers())
                {
                    System.out.println("    memberUsername = " + mem.getUsername());
                }
            }


            /**
             * 컬렉션 Fetch Join 페이징 사용(정상)
             * 다대일인 경우
             */
            /*String query = "select m from Member m join fetch m.team";
            List<Member> resultList = em.createQuery(query, Member.class)
                    .setFirstResult(0)
                    .setMaxResults(1)
                    .getResultList();
            System.out.println("size = " + resultList.size());

            for(Member mem : resultList)
            {
                System.out.println("memberUsername = " + mem.getUsername() + " # teamName = " + mem.getTeam().getName());
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
