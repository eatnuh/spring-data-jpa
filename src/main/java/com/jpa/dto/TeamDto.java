package com.jpa.dto;

import com.jpa.domain.Team;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@Getter
public class TeamDto {

    private long id;
    private String name;
    private List<String> members;

    public TeamDto(long id, String name, List<String> members) {
        this.id = id;
        this.name = name;
        this.members = members;
    }

    public static TeamDto fromEntity(Team team) {
        return new TeamDto(
                team.getId(),
                team.getName(),
                team.getMembers().stream().map(member -> member.getName()).collect(Collectors.toList())
        );
    }
}
