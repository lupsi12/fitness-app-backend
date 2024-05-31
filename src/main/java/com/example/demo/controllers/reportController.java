package com.example.demo.controllers;

import com.example.demo.entities.report;
import com.example.demo.requests.ReportCreateRequest;
import com.example.demo.responses.reportResponses;
import com.example.demo.services.reportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/report")
public class reportController {
    @Autowired
    private reportService reportService;

    public reportController(reportService reportService) {
        this.reportService = reportService;
    }
    @GetMapping
    public List<reportResponses> getAllReports(@RequestParam Optional<Long> clientId, @RequestParam Optional<Long> coachId){
        return reportService.getAllReports(clientId,coachId);
    }
    @PostMapping
    public report createOneReport(@RequestBody ReportCreateRequest reportCreateRequest){
        return reportService.saveOneRepost(reportCreateRequest);
    }
    @GetMapping("/{reportId}")
    public report getOneReport(@PathVariable Long reportId){
        return reportService.getOneReport(reportId);
    }
    @PutMapping("/{reportId}")
    public report updateOneReport(@PathVariable Long reportId,@RequestBody report newReport){
        return  reportService.updateOneReport(reportId,newReport);
    }
    @DeleteMapping("/{reportId}")
    public void  deleteOneUser(@PathVariable Long reportId){
        reportService.deleteOneReport(reportId);
    }
}
