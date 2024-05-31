package com.example.demo.services;

import com.example.demo.entities.client;
import com.example.demo.entities.coach;
import com.example.demo.entities.nutritionplan;
import com.example.demo.repos.NutritionplanRepository;
import com.example.demo.responses.NutritionplanResponses;
import com.example.demo.requests.NutritionCreateRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class nutritionplanService {
    private NutritionplanRepository nutritionplanRepository;
    private clientService clientService;
    private coachService coachService;

    public nutritionplanService(NutritionplanRepository nutritionplanRepository, com.example.demo.services.clientService clientService, com.example.demo.services.coachService coachService) {
        this.nutritionplanRepository = nutritionplanRepository;
        this.clientService = clientService;
        this.coachService = coachService;
    }

    public List<NutritionplanResponses> getAllNutritionplans(Optional<Long> clientId, Optional<Long> coachId) {
        List<nutritionplan> nutritionplan;
        if(clientId.isPresent() && coachId.isPresent()){
            nutritionplan = nutritionplanRepository.findByClientIdAndCoachId(clientId.get(),coachId.get());
        } else if (clientId.isPresent()) {
            nutritionplan = nutritionplanRepository.findByClientId(clientId.get());
        }
        else if (coachId.isPresent()) {
            nutritionplan = nutritionplanRepository.findByCoachId(coachId.get());
        }else
            nutritionplan = nutritionplanRepository.findAll();
        return nutritionplan.stream().map(nutritionplann -> new NutritionplanResponses(nutritionplann)).collect(Collectors.toList());
    }

    public nutritionplan saveOneNutritionplan(NutritionCreateRequest nutritionCreateRequest) {
        client client = clientService.getOneClient(nutritionCreateRequest.getClient_id());
        coach coach = coachService.getOneCoach(nutritionCreateRequest.getCoach_id());
        if(client != null && coach != null){
            nutritionplan toSave = new nutritionplan();
            toSave.setCalori(nutritionCreateRequest.getCalori());
            toSave.setMeal(nutritionCreateRequest.getMeal());
            toSave.setTarget(nutritionCreateRequest.getTarget());
            toSave.setId(nutritionCreateRequest.getId());
            toSave.setCoach(coach);
            toSave.setClient(client);
            return nutritionplanRepository.save(toSave);
        }else
            return null;
    }

    public nutritionplan getOneNotritionplan(Long nutritionplanId) {
        return nutritionplanRepository.findById(nutritionplanId).orElse(null);
    }

    public nutritionplan updateOneNutritionplan(Long nutritionplanId, nutritionplan newNutritionplan) {
        Optional<nutritionplan> nutritionplan = nutritionplanRepository.findById(nutritionplanId);
        if(nutritionplan.isPresent()){
            nutritionplan foundNutritionplan = nutritionplan.get();
            foundNutritionplan.setClient(newNutritionplan.getClient());
            foundNutritionplan.setCoach(newNutritionplan.getCoach());
            foundNutritionplan.setTarget(newNutritionplan.getTarget());
            foundNutritionplan.setMeal(newNutritionplan.getMeal());
            foundNutritionplan.setCalori(newNutritionplan.getCalori());
            nutritionplanRepository.save(foundNutritionplan);
            return foundNutritionplan;
        }else
            return null;
    }

    public void deleteOneNutritionplan(Long nutritionplanId) {
        nutritionplanRepository.deleteById(nutritionplanId);
    }
}
