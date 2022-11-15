package com.jpa.dto;

import com.jpa.domain.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class MemberDto {

    private long id;
    private String name;
    private String teamName;

    public MemberDto(long id, String name, String teamName) {
        this.id = id;
        this.name = name;
        this.teamName = teamName;
    }

    public static MemberDto fromEntity(Member member) {
        return new MemberDto(member.getId(), member.getName(), member.getTeam().getName());
    }
}
