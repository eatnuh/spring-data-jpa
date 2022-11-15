package com.jpa.Repository;

import com.jpa.domain.Member;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface MemberRepository extends Repository<Member, Long> {

    Member save(Member member1);

    List<Member> findAll();

    @Query("select m from Member m join fetch m.team")
    List<Member> findAllUsingJoinFetch();

    @EntityGraph(attributePaths = "team")
    @Query("select m from Member m")
    List<Member> findAllUsingEntityGraph();
}
