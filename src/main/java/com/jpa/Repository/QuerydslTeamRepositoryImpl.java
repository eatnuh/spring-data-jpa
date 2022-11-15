package com.jpa.Repository;

import com.jpa.domain.Team;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

import static com.jpa.domain.QMember.member;
import static com.jpa.domain.QTeam.team;

public class QuerydslTeamRepositoryImpl extends QuerydslRepositorySupport implements QuerydslTeamRepository {

    private final JPAQueryFactory jpaQueryFactory;

    public QuerydslTeamRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
        super(Team.class);
        this.jpaQueryFactory = jpaQueryFactory;
    }

    @Override
    public List<Team> findAllUsingQueryDsl() {
        return jpaQueryFactory.selectFrom(team)
                .leftJoin(team.members, member)
                .fetchJoin()
                .distinct()
                .fetch();
    }
}
