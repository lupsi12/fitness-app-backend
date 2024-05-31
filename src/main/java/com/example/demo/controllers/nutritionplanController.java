package com.example.demo.controllers;

import com.example.demo.entities.nutritionplan;
import com.example.demo.requests.NutritionCreateRequest;
import com.example.demo.responses.NutritionplanResponses;
import com.example.demo.services.nutritionplanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/nutritionplan")
public class nutritionplanController {
    @Autowired
    private nutritionplanService nutritionplanService;

    public nutritionplanController(nutritionplanService nutritionplanService) {
        this.nutritionplanService = nutritionplanService;
    }
    @GetMapping
    public List<NutritionplanResponses> getAllNutritionplans(@RequestParam Optional<Long> clientId, @RequestParam Optional<Long> coachId){
        return nutritionplanService.getAllNutritionplans(clientId,coachId);
    }
    @PostMapping
    public nutritionplan createNutritionplan(@RequestBody NutritionCreateRequest nutritionCreateRequest){
        return nutritionplanService.saveOneNutritionplan(nutritionCreateRequest);
    }
    @GetMapping("/{nutritionplanId}")
    public nutritionplan getOneNutritionplan(@PathVariable Long nutritionplanId){
        return nutritionplanService.getOneNotritionplan(nutritionplanId);
    }
    @PutMapping("/{nutritionplanId}")
    public nutritionplan updateOneNutritionplan(@PathVariable Long nutritionplanId,@RequestBody nutritionplan newNutritionplan){
        return nutritionplanService.updateOneNutritionplan(nutritionplanId,newNutritionplan);
    }
    @DeleteMapping("/{nutritionplanId}")
    public void deleteOneNutritionplan(@PathVariable Long nutritionplanId){
        nutritionplanService.deleteOneNutritionplan(nutritionplanId);
    }
}
