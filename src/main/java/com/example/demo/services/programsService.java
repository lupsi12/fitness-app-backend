package com.example.demo.services;

import com.example.demo.entities.client;
import com.example.demo.entities.coach;
import com.example.demo.entities.programs;
import com.example.demo.repos.ProgramsRepository;
import com.example.demo.requests.ProgramsCreateRequest;
import com.example.demo.responses.programsResponses;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class programsService {
    private ProgramsRepository programsRepository;
    private clientService clientService;
    private coachService coachService;

    public programsService(ProgramsRepository programsRepository, com.example.demo.services.clientService clientService, com.example.demo.services.coachService coachService) {
        this.programsRepository = programsRepository;
        this.clientService = clientService;
        this.coachService = coachService;
    }

    public List<programsResponses> getAllPrograms(Optional<Long> clientId, Optional<Long> coachId){
        List<programs> programs;
        if(clientId.isPresent() && coachId.isPresent()){
            programs = programsRepository.findByClientIdAndCoachId(clientId.get(),coachId.get());
        } else if (clientId.isPresent()) {
            programs = programsRepository.findByClientId(clientId.get());
        }
        else if (coachId.isPresent()) {
            programs = programsRepository.findByCoachId(coachId.get());
        }else
            programs = programsRepository.findAll();
        return programs.stream().map(programss -> new programsResponses(programss)).collect(Collectors.toList());
    }

    public programs saveOnePrograms(ProgramsCreateRequest programsCreateRequest) {
        client client = clientService.getOneClient(programsCreateRequest.getClient_id());
        coach coach = coachService.getOneCoach(programsCreateRequest.getCoach_id());
        if(client != null && coach != null){
            programs toSave = new programs();
            toSave.setProgramduration(programsCreateRequest.getProgramduration());
            toSave.setEgzersize(programsCreateRequest.getEgzersize());
            toSave.setVideoguides(programsCreateRequest.getVideoguides());
            toSave.setSet(programsCreateRequest.getSet());
            toSave.setTarget(programsCreateRequest.getTarget());
            toSave.setProgramstartdate(programsCreateRequest.getProgramstartdate());
            toSave.setId(programsCreateRequest.getId());
            toSave.setCoach(coach);
            toSave.setClient(client);
            return programsRepository.save(toSave);
        }else
            return null;
    }

    public programs getOnePrograms(Long programsId) {
        return programsRepository.findById(programsId).orElse(null);
    }

    public programs updateOnePrograms(Long programsId, programs newPrograms) {
        Optional<programs> programs = programsRepository.findById(programsId);
        if(programs.isPresent()){
            programs foundPrograms = programs.get();
            foundPrograms.setClient(newPrograms.getClient());
            foundPrograms.setCoach(newPrograms.getCoach());
            foundPrograms.setEgzersize(newPrograms.getEgzersize());
            foundPrograms.setTarget(newPrograms.getTarget());
            foundPrograms.setSet(newPrograms.getSet());
            foundPrograms.setVideoguides(newPrograms.getVideoguides());
            foundPrograms.setProgramstartdate(newPrograms.getProgramstartdate());
            foundPrograms.setProgramduration(newPrograms.getProgramduration());
            programsRepository.save(foundPrograms);
            return foundPrograms;
        }else
            return null;
    }

    public void deleteOnePrograms(Long programsId) {
        programsRepository.deleteById(programsId);
    }
}
