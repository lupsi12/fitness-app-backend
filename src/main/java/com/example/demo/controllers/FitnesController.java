package com.example.demo.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.services.FitnesService;

@RestController
@RequestMapping("/fitnes")
public class FitnesController {
    private FitnesService fitnesService;

    public FitnesController(FitnesService fitnesService) {
        this.fitnesService = fitnesService;
    }
    
    
}
