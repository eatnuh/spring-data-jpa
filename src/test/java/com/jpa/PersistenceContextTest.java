package com.jpa;

import com.jpa.domain.Item;
import com.jpa.domain.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class PersistenceContextTest {

    @Autowired
    private EntityManagerFactory emf;

    private void initializeMember() {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(new Member("mike"));
        em.persist(new Member("jane"));
        em.persist(new Member("john"));
        tx.commit();
        em.close();
    }

    @Test
    @DisplayName("1차캐시 - 동일성보장")
    void test1() {
        //given
        initializeMember();

        //when
        EntityManager em = emf.createEntityManager();
        Member member1 = em.find(Member.class, 1L);
        Member member2 = em.find(Member.class, 1L);

        //then
        assertThat(member1 == member2).isEqualTo(true);
        em.close();
    }

    @Test
    @DisplayName("쓰기지연 - INSERT 쿼리 COMMIT 후 실행")
    void test2() {

        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        Item item1 = new Item("001", "notebook");
        Item item2 = new Item("002", "phone");
        Item item3 = new Item("003", "watch");

        em.persist(item1);
        em.persist(item2);
        em.persist(item3);

        System.out.println("쓰기지연 발생");

        tx.commit();
        em.close();
    }

    @Test
    @DisplayName("쓰기지연 발생안함 - 식별자 DB에 의해 생성(@GeneratedValue)")
    void test3() {

        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        Member member1 = new Member( "john");
        Member member2 = new Member( "jane");
        Member member3 = new Member( "mike");

        em.persist(member1);
        em.persist(member2);
        em.persist(member3);

        System.out.println("쓰기지연 발생안함");

        tx.commit();
        em.close();
    }

    @Test
    @DisplayName("변경감지 - 엔티티변경 -> UPDATE 쿼리 발생")
    void test4() {
        initializeMember();

        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        Member member = em.find(Member.class, 1L);
        member.setUsername("coco");

        tx.commit();
        em.close();
    }

    @Test
    @DisplayName("플러시 - flush() 직접호출")
    void test5() {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        Item item = new Item("001", "phone");
        em.persist(item);
        em.flush();
        System.out.println("INSERT 쿼리 발생");

        tx.commit();
        em.close();
    }

    @Test
    @DisplayName("준영속 -  영속성 컨텍스트 관리하지 않는다")
    void test6() {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        Item item = new Item("001", "phone");
        em.persist(item);
        em.detach(item);

        System.out.println("INSERT 쿼리 발생 안함");

        tx.commit();
        em.close();
    }
}
