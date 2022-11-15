package com.jpa.Repository;

import com.jpa.domain.Member;
import com.jpa.domain.Team;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class MemberRepositoryTest {

    @Autowired MemberRepository memberRepository;

    @Autowired TeamRepository teamRepository;

    @Test
    void testSelect() {
        Team team = new Team();
        team.setName("New Team");
        Team savedTeam = teamRepository.save(team);

        Team team2 = new Team();
        team2.setName("New team 2");
        Team savedTeam2 = teamRepository.save(team2);

        Member member1 = new Member();
        member1.setName("taehoon");
        member1.setTeam(savedTeam);
        memberRepository.save(member1);

        Member member2 = new Member();
        member2.setName("seulki");
        member2.setTeam(savedTeam2);
        memberRepository.save(member2);

        memberRepository.findAll().forEach(System.out::println);

    }

}