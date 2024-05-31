package com.example.demo.controllers;

import com.example.demo.entities.programs;
import com.example.demo.requests.ProgramsCreateRequest;
import com.example.demo.responses.programsResponses;
import com.example.demo.services.programsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/programs")
public class programsController {
    @Autowired
    private programsService programsService;

    public programsController(programsService programsService) {
        this.programsService = programsService;
    }
    @GetMapping
    public List<programsResponses> getAllPrograms(@RequestParam Optional<Long> clientId, @RequestParam Optional<Long> coachId){
        return programsService.getAllPrograms(clientId,coachId);
    }
    @PostMapping
    public programs createPrograms(@RequestBody ProgramsCreateRequest programsCreateRequest){
        return programsService.saveOnePrograms(programsCreateRequest);
    }
    @GetMapping("/{programsId}")
    public programs getOnePrograms(@PathVariable Long programsId){
        return programsService.getOnePrograms(programsId);
    }

    @PutMapping("/{programsId}")
    public programs updateOnePrograms(@PathVariable Long programsId,@RequestBody programs newPrograms){
        return programsService.updateOnePrograms(programsId,newPrograms);
    }
    @DeleteMapping("/{programsId}")
    public void deleteOneprograms(@PathVariable Long programsId){
        programsService.deleteOnePrograms(programsId);
    }
}
