package com.jpa;

import com.jpa.domain.Department;
import com.jpa.domain.Employee;
import com.jpa.domain.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.util.List;

@DataJpaTest
public class AssociationMappingTest {

    @Autowired
    private EntityManagerFactory emf;

    private void initializeEmployeeAndDepartment() {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        Department department = new Department("인사");
        em.persist(department);

        Employee employee1 = new Employee("john");
        Employee employee2 = new Employee("mike");

        employee1.setDepartment(department);
        em.persist(employee1);

        employee2.setDepartment(department);
        em.persist(employee2);
        tx.commit();
        em.close();
    }



    @Test
    @DisplayName("연관관계 매핑 - 저장")
    void test1() {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        Department department = new Department("인사");
        em.persist(department);

        Employee employee1 = new Employee("john");
        Employee employee2 = new Employee("mike");

        employee1.setDepartment(department);
        em.persist(employee1);

        employee2.setDepartment(department);
        em.persist(employee2);

        tx.commit();

        System.out.println(employee1);
        System.out.println(employee2);

        em.close();
    }

    @Test
    @DisplayName("연관관계 매핑 - 조회(객체 그래프 탐색)")
    void test2() {
        initializeEmployeeAndDepartment();

        EntityManager em = emf.createEntityManager();

        Employee employee = em.find(Employee.class, 1L);
        Department department = employee.getDepartment();

        System.out.println(employee);
        System.out.println(department);

        em.close();
    }

    @Test
    @DisplayName("연관관계 매핑 - 조회(JPQL)")
    void test3() {
        initializeEmployeeAndDepartment();

        EntityManager em = emf.createEntityManager();

        List<Employee> employees = em.createQuery("select e from Employee e", Employee.class).getResultList();

        employees.stream().forEach(System.out::println);

        em.close();
    }

    @Test
    @DisplayName("연관관계 매핑 - 수정")
    void test4() {
        initializeEmployeeAndDepartment();

        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        Department department = new Department("법무");
        em.persist(department);

        Employee employee = em.find(Employee.class, 1L);
        employee.setDepartment(department);

        tx.commit();
        em.close();
    }

    @Test
    @DisplayName("연관관계 매핑 - 삭제")
    void test5() {
        initializeEmployeeAndDepartment();

        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        Employee employee = em.find(Employee.class, 1L);
        employee.setDepartment(null);

        tx.commit();
        em.close();
    }
}
