package com.jpa.Repository;

import com.jpa.domain.Team;

import java.util.List;

public interface QuerydslTeamRepository {

    List<Team> findAllUsingQueryDsl();

}
