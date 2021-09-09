package jpql;

import jpql.Domain.Member;
import jpql.Domain.MemberType;
import jpql.Domain.Team;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class CaseMain {
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            Member member = new Member();
            member.setAge(10);
            member.setUsername("Member");
            member.setType(MemberType.ADMIN);

            Team team = new Team();
            team.setName("Team");
            member.addteam(team);

            em.persist(team);
            em.persist(member);
            
            em.flush();
            em.clear();

            // 기본 Case 식
            /*String query = "select " +
                                "case when m.age <= 10 then '학생요금' " +
                                     "when m.age >= 60 then '경로요금' " +
                                     "else '일반요금' " +
                                     "end " +
                    " from Member m";

            List<String> resultList = em.createQuery(query, String.class).getResultList();
            for (String s : resultList) {
                System.out.println("s = " + s);
            }*/

            // 단순 Case 식
            /*String query = "select " +
                                "case t.name " +
                                     "when 'Team' then '110%' " +
                                     "when 'TeamB' then '120%' " +
                                     "else '105%' " +
                                     "end " +
                    " from Team t";

            List<String> resultList = em.createQuery(query, String.class).getResultList();
            for (String s : resultList) {
                System.out.println("s = " + s);
            }*/

            // 조건 Case 식(coalesce)
            /*Member nullMember = new Member();
            nullMember.setAge(10);
            nullMember.setUsername(null);
            nullMember.setType(MemberType.ADMIN);

            Team teamB = new Team();
            teamB.setName("TeamB");
            nullMember.addteam(teamB);

            em.persist(teamB);
            em.persist(nullMember);

            String query = "select coalesce(m.username, '이름 없는 회원') from Member m";

            List<String> resultList = em.createQuery(query, String.class).getResultList();
            for (String s : resultList) {
                System.out.println("s = " + s);
            }*/

            // 조건 Case 식(nullif)
            Member nullMember = new Member();
            nullMember.setAge(10);
            nullMember.setUsername("관리자");
            nullMember.setType(MemberType.ADMIN);
            em.persist(nullMember);

            String query = "select nullif(m.username, '관리자') from Member m";

            List<String> resultList = em.createQuery(query, String.class).getResultList();
            for (String s : resultList) {
                System.out.println("s = " + s);
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
