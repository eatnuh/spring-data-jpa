package com.jpa.Repository;

import com.jpa.domain.Team;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Set;

public interface TeamRepository extends Repository<Team, Long>, QuerydslTeamRepository {

    Team save(Team team);

    List<Team> findAll();

    @Query("select t from Team t join fetch t.members")
    Set<Team> findAllUsingJoinFetch();

    @EntityGraph(attributePaths = "members")
    @Query("select t from Team t")
    Set<Team> findAllUsingEntityGraph();
}
