package com.example.demo.services;

import com.example.demo.entities.client;
import com.example.demo.entities.coach;
import com.example.demo.entities.team;
import com.example.demo.repos.TeamRepository;
import com.example.demo.requests.TeamCreateRequest;
import com.example.demo.responses.teamResponses;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class teamService {
    private TeamRepository teamRepository;
    private clientService clientService;
    private coachService coachService;

    public teamService(TeamRepository teamRepository,clientService clientService,coachService coachService) {
        this.teamRepository = teamRepository;
        this.clientService = clientService;
        this.coachService = coachService;
    }

    public List<teamResponses> getAllTeams(Optional<Long> clientId, Optional<Long> coachId) {
        List<team> team;
        if(clientId.isPresent() && coachId.isPresent()){
            team = teamRepository.findByClientIdAndCoachId(clientId.get(),coachId.get());
        } else if (clientId.isPresent()) {
            team = teamRepository.findByClientId(clientId.get());
        }
        else if (coachId.isPresent()) {
            team = teamRepository.findByCoachId(coachId.get());
        }else
            team = teamRepository.findAll();
        return team.stream().map(teams -> new teamResponses(teams)).collect(Collectors.toList());
    }

    public team saveOneTeam(TeamCreateRequest teamCreateRequest) {
        client client = clientService.getOneClient(teamCreateRequest.getClient_id());
        coach coach = coachService.getOneCoach(teamCreateRequest.getCoach_id());
        if(client != null && coach != null){
            team toSave = new team();
            toSave.setCoach(coach);
            toSave.setClient(client);
            toSave.setId(teamCreateRequest.getId());
            return teamRepository.save(toSave);
        }else
            return null;
    }

    public team getOneTeam(Long teamId) {
        return teamRepository.findById(teamId).orElse(null);
    }

    public team updateOneTeam(Long teamId, team newTeam) {
        Optional<team> team = teamRepository.findById(teamId);
        if(team.isPresent()){
            team foundTeam = team.get();
            foundTeam.setClient(newTeam.getClient());
            foundTeam.setCoach(newTeam.getCoach());
            teamRepository.save(foundTeam);
            return foundTeam;
        }else
            return null;
    }
}
