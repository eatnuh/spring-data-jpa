package com.jpa.api;

import com.jpa.Repository.MemberRepository;
import com.jpa.dto.MemberDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/member")
@RestController
public class MemberController {

    private final MemberRepository memberRepository;

    public MemberController(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @GetMapping("/N+1")
    public List<MemberDto> findAll() {
        return memberRepository.findAll().stream()
                .map(MemberDto::fromEntity)
                .collect(Collectors.toList());
    }

    @GetMapping("/UsingJoinFetch")
    public List<MemberDto> findAllUsingJoinFetch() {
        return memberRepository.findAllUsingJoinFetch().stream()
                .map(MemberDto::fromEntity)
                .collect(Collectors.toList());
    }

    @GetMapping("/UsingEntityGraph")
    public List<MemberDto> findAllUsingEntityGraph() {
        return memberRepository.findAllUsingEntityGraph().stream()
                .map(MemberDto::fromEntity)
                .collect(Collectors.toList());
    }
}

