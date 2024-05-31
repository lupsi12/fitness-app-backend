package com.example.demo.services;

import com.example.demo.entities.coach;
import com.example.demo.entities.user;
import com.example.demo.repos.CoachRepository;
import com.example.demo.requests.CoachCreateRequest;
import com.example.demo.responses.adminResponses;
import com.example.demo.responses.coachResponses;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class coachService {

    private CoachRepository coachRepository;
    private userService userService;

    public coachService(CoachRepository coachRepository,userService userService) {
        this.coachRepository = coachRepository;
        this.userService = userService;
    }

    public List<coachResponses> getAllCoach(Optional<Long> userId) {
        List<coach> list;
        if(userId.isPresent())
            list = coachRepository.findByUserId(userId);
        else
            list = coachRepository.findAll();
        return list.stream().map(p -> new coachResponses(p)).collect(Collectors.toList());
    }

    public coach saveOneCoach(CoachCreateRequest coachCreateRequest) {
        user user = userService.getOneUser(coachCreateRequest.getUser_id());
        if(user == null)
            return null;
        coach toSave = new coach();
        toSave.setUser(user);
        toSave.setId(coachCreateRequest.getId());
        toSave.setProfession(coachCreateRequest.getProfession());
        toSave.setExperience(coachCreateRequest.getExperience());
        return coachRepository.save(toSave);
    }

    public coach getOneCoach(Long coachId) {
        return coachRepository.findById(coachId).orElse(null);
    }

    public coach updateOneCoach(Long coachId, coach newCoach) {
        Optional<coach> coach = coachRepository.findById(coachId);
        if (coach.isPresent()) {
            coach foundCoach = coach.get();
            foundCoach.setUser(newCoach.getUser());
            foundCoach.setExperience(newCoach.getExperience());
            foundCoach.setProfession(newCoach.getProfession());
            coachRepository.save(foundCoach);
            return foundCoach;
        } else
            return null;
    }

    public void deleteOneCoach(Long coachId) {
        coachRepository.deleteById(coachId);
    }
}
