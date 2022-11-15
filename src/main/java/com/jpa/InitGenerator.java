package com.jpa;

import com.jpa.Repository.MemberRepository;
import com.jpa.Repository.TeamRepository;
import com.jpa.domain.Member;
import com.jpa.domain.Team;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class InitGenerator implements ApplicationRunner {

    private final MemberRepository memberRepository;
    private final TeamRepository teamRepository;

    public InitGenerator(MemberRepository memberRepository, TeamRepository teamRepository) {
        this.memberRepository = memberRepository;
        this.teamRepository = teamRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Team team1 = createTeam("team 1");
        Team team2 = createTeam("team 2");
        Team team3 = createTeam("team 3");

        createMember("Teahoon", team1);
        createMember("Mike", team1);
        createMember("Sujan", team1);
        createMember("Suji", team1);
        createMember("Bread", team1);
        createMember("Jolly", team1);
        createMember("Bob", team2);
        createMember("Wang", team2);
        createMember("Tom", team2);
        createMember("Luffy", team2);
        createMember("Jane", team2);
        createMember("Miso", team3);
        createMember("Hansu", team3);
        createMember("Radi", team3);
        createMember("Moll", team3);
        createMember("Ken", team3);

    }

    private Team createTeam(String teamName) {
        Team team = new Team();
        team.setName(teamName);
        return teamRepository.save(team);
    }

    private Member createMember(String name, Team team) {
        Member member = new Member();
        member.setName(name);
        member.setTeam(team);
        return memberRepository.save(member);
    }


}
