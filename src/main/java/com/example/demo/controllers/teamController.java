package com.example.demo.controllers;

import com.example.demo.entities.team;
import com.example.demo.requests.TeamCreateRequest;
import com.example.demo.responses.teamResponses;
import com.example.demo.services.teamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/team")
public class teamController {
    @Autowired
    private teamService teamService;

    public teamController(teamService teamService) {
        this.teamService = teamService;
    }

    @GetMapping
    public List<teamResponses> getAllTeams(@RequestParam Optional<Long> clientId, @RequestParam Optional<Long> coachId){
        return teamService.getAllTeams(clientId,coachId);
    }
    @PostMapping
    public team createOneTeam(@RequestBody TeamCreateRequest teamCreateRequest){
        return  teamService.saveOneTeam(teamCreateRequest);
    }
    @GetMapping("/{teamId}")
    public team getOneTeam(@PathVariable Long teamId){
        return teamService.getOneTeam(teamId);
    }
    @PutMapping("/{teamId}")
    public team updateOneTeam(@PathVariable Long teamId,@RequestBody team newTeam){
        return teamService.updateOneTeam(teamId,newTeam);
    }
}
