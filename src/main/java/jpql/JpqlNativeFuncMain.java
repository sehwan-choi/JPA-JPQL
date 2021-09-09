package jpql;

import jpql.Domain.Member;
import jpql.Domain.MemberType;
import jpql.Domain.Team;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpqlNativeFuncMain {

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

            /**
             * Concat
             * 설명 : 문자열 이어붙이기
             * 결과 : 이어붙인 문자열
             */
            /*String query = "select concat('a','b') from Member m";
            //String query = "select 'a' || 'b' from Member m";
            List<String> resultList = em.createQuery(query, String.class).getResultList();

            for (String s : resultList) {
                System.out.println("s = " + s);
            }*/

            /**
             * Substring
             * 설명 : 문자열 자르기
             * 결과 : 전체 문자열에서 n번째 부터 m개만큼 잘려진 문자열( substring(문자열, n,m) )
             */
            /*String query = "select substring(m.username, 2,3) from Member m";
            List<String> resultList = em.createQuery(query, String.class).getResultList();

            for (String s : resultList) {
                System.out.println("s = " + s);
            }*/

            /**
             * Lower
             * 설명 : 문자열을 소문자로 변환
             * 결과 : 소문자로 변환된 문자열 반환
             */
            /*String query = "select lower('ABCDE') from Member m";
            List<String> resultList = em.createQuery(query, String.class).getResultList();

            for (String s : resultList) {
                System.out.println("s = " + s);
            }*/

            /**
             * Upper
             * 설명 : 문자열을 대문자로 변환
             * 결과 : 대문자로 변환된 문자열 반환
             */
            /*String query = "select lower('abcde') from Member m";
            List<String> resultList = em.createQuery(query, String.class).getResultList();

            for (String s : resultList) {
                System.out.println("s = " + s);
            }*/

            /**
             * Trim
             * 설명 : 공백 제거
             * 결과 : 공백 제거된 문자열
             */
            Member trimMember = new Member();
            trimMember.setAge(10);
            trimMember.setUsername("    trimMember");
            trimMember.setType(MemberType.ADMIN);
            em.persist(trimMember);

            em.flush();
            em.clear();

            String query = "select trim(m.username) from Member m";
            List<String> resultList = em.createQuery(query, String.class).getResultList();

            for (String s : resultList) {
                System.out.println("s = " + s);
            }

            /**
             * Length
             * 설명 : 문자열 길이 구하기
             * 결과 : 문자열의 길이(Integer)
             */
            /*String query = "select length(m.username) from Member m";
            List<Integer> resultList = em.createQuery(query, Integer.class).getResultList();

            for (Integer s : resultList) {
                System.out.println("s = " + s);
            }*/

            /**
             * Locate
             * 설명 : 일치하는 문자열이 있는지 확인
             * 결과 : 일치하는 문자열이 위치하는 번째수(Integer)
             */
            /*String query = "select locate('de','abcdefg') from Member m";
            List<Integer> resultList = em.createQuery(query, Integer.class).getResultList();

            for (Integer s : resultList) {
                System.out.println("s = " + s);
            }*/

            /**
             * Abs
             * 설명 : 절대값
             * 결과 : 절대값 반환
             */
            /*String query = "select abs(-3) from Member m";
            List<Integer> resultList = em.createQuery(query, Integer.class).getResultList();

            for (Integer s : resultList) {
                System.out.println("s = " + s);
            }*/


            /**
             * Sqrt
             * 설명 : 제곱근
             * 결과 : 제곱근 반환(Double)
             */
            /*String query = "select sqrt(16) from Member m";
            List<Double> resultList = em.createQuery(query, Double.class).getResultList();

            for (Double s : resultList) {
                System.out.println("s = " + s);
            }*/


            /**
             * Mod
             * 설명 : 나머지 구하기
             * 결과 : m을 n으로 나누어 남은 값을 반환한다. (Mod(m,n) 과 같이 사용)
             */
            /*String query = "select mod(5,3) from Member m";
            List<Integer> resultList = em.createQuery(query, Integer.class).getResultList();

            for (Integer s : resultList) {
                System.out.println("s = " + s);
            }*/

            /**
             * Size
             * 설명 : JPA가 지원하는 함수로 연관관계가 있는 엔티티의 개수를 확인할 수 있다.
             * 결과 : 연관관계가 있는 엔티티의 개수를 반환
             */
            /*String query = "select size(t.members) from Team t";
            List<Integer> resultList = em.createQuery(query, Integer.class).getResultList();

            for (Integer s : resultList) {
                System.out.println("s = " + s);
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
