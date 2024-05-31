package com.example.demo.controllers;

import com.example.demo.entities.coach;
import com.example.demo.repos.CoachRepository;
import com.example.demo.requests.ClientCreateRequest;
import com.example.demo.requests.CoachCreateRequest;
import com.example.demo.responses.coachResponses;
import com.example.demo.services.coachService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/coach")
public class coachController {
    @Autowired
    private coachService coachService;

    public coachController(coachService coachService) {
        this.coachService = coachService;
    }

    @GetMapping
    public List<coachResponses> getAllCoach(@RequestParam Optional<Long> userId) {
        return coachService.getAllCoach(userId);
    }

    @PostMapping
    public coach coachCreate(@RequestBody CoachCreateRequest coachCreateRequest) {
        return coachService.saveOneCoach(coachCreateRequest);
    }

    @GetMapping("/{coachId}")
    public coach getOneCoach(@PathVariable Long coachId) {
        return coachService.getOneCoach(coachId);
    }

    @PutMapping("/{coachId}")
    public coach updateOneCoach(@PathVariable Long coachId, @RequestBody coach newCoach) {
        return coachService.updateOneCoach(coachId,newCoach);
    }
    @DeleteMapping("/{coachId}")
    public void deleteOneCoach(@PathVariable Long coachId){
        coachService.deleteOneCoach(coachId);
    }
}