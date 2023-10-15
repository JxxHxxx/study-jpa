package jpabasic.ex1hellojpa;

import javax.persistence.*;
import java.util.List;

/** JPQL
 * JPQL 은 엔티티를 대상으로 쿼리 | SQL 은 테이블을 대상으로 쿼리
 * SQL을 추상화해서 특정 데이터베이스 SQL에 의존 X
 * JPQL을 한마디로 정의하면 객체 지향 SQL
 *
 */

public class JpaMain {

    public static void main(String[] args) {
        System.out.println("hello");
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        // 쓰레드간 공유하면 안된다.
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        // JPA 모든 데이터 변경은 트랜잭션 안에서 실행한다.
        try {
            tx.begin();

            Member member = new Member(20L, "HiC", 20);
            em.persist(member);

            member.setName("HiA");

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close(); // 엔티티 매니저가 데이터베이스 커넥션을 물고 동작한다고 함
        }
        emf.close();
    }

    public void printMember(EntityManager em) {
        List<Member> findMembers = em.createQuery("select m from Member as m", Member.class)
                .getResultList();

        for (Member findMember : findMembers) {
            System.out.println(findMember);
        }
    }
}
