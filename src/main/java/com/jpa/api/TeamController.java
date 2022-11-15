package com.jpa.api;

import com.jpa.Repository.TeamRepository;
import com.jpa.dto.TeamDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/team")
public class TeamController {

    private final TeamRepository teamRepository;

    public TeamController(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @GetMapping("/N+1")
    public List<TeamDto> findAll() {
        return teamRepository.findAll().stream()
                .map(TeamDto::fromEntity)
                .collect(Collectors.toList());
    }

    @GetMapping("/UsingJoinFetch")
    public List<TeamDto> findAllUsingJoinFetch() {
        return teamRepository.findAllUsingJoinFetch().stream()
                .map(TeamDto::fromEntity)
                .collect(Collectors.toList());
    }

    @GetMapping("/UsingEntityGraph")
    public List<TeamDto> findAllUsingEntityGraph() {
        return teamRepository.findAllUsingEntityGraph().stream()
                .map(TeamDto::fromEntity)
                .collect(Collectors.toList());
    }
}
