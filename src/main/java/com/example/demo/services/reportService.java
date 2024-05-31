package com.example.demo.services;

import com.example.demo.entities.client;
import com.example.demo.entities.coach;
import com.example.demo.entities.report;
import com.example.demo.repos.ReportRepository;
import com.example.demo.requests.ReportCreateRequest;
import com.example.demo.responses.reportResponses;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class reportService {
    private ReportRepository reportRepository;
    private clientService clientService;
    private coachService coachService;

    public reportService(ReportRepository reportRepository,clientService clientService, coachService coachService) {
        this.reportRepository = reportRepository;
        this.clientService = clientService;
        this.coachService = coachService;
    }

    public List<reportResponses> getAllReports(Optional<Long> clientId, Optional<Long> coachId) {
        List<report> report;
        if(clientId.isPresent() && coachId.isPresent()){
            report = reportRepository.findByClientIdAndCoachId(clientId.get(),coachId.get());
        } else if (clientId.isPresent()) {
            report = reportRepository.findByClientId(clientId.get());
        }
        else if (coachId.isPresent()) {
            report = reportRepository.findByCoachId(coachId.get());
        }else
            report = reportRepository.findAll();
        return report.stream().map(reportt -> new reportResponses(reportt)).collect(Collectors.toList());
    }

    public report saveOneRepost(ReportCreateRequest reportCreateRequest) {
        client client = clientService.getOneClient(reportCreateRequest.getClient_id());
        coach coach = coachService.getOneCoach(reportCreateRequest.getCoach_id());
        if(client != null && coach != null){
            report toSave = new report();
            toSave.setCoach(coach);
            toSave.setClient(client);
            toSave.setId(reportCreateRequest.getId());
            toSave.setHeight(reportCreateRequest.getHeight());
            toSave.setWeight(reportCreateRequest.getWeight());
            toSave.setBodymassindex(reportCreateRequest.getBodymassindex());
            toSave.setBodyfatradio(reportCreateRequest.getBodyfatradio());
            toSave.setMusclemass(reportCreateRequest.getMusclemass());
            return reportRepository.save(toSave);
        }else
            return null;
    }
    public report getOneReport(Long reportId) {
        return reportRepository.findById(reportId).orElse(null);
    }

    public report updateOneReport(Long reportId, report newReport) {
        Optional<report> report = reportRepository.findById(reportId);
        if(report.isPresent()){
            report foundReport = report.get();
            foundReport.setClient(newReport.getClient());
            foundReport.setCoach(newReport.getCoach());
            foundReport.setHeight(newReport.getHeight());
            foundReport.setWeight(newReport.getWeight());
            foundReport.setBodyfatradio(newReport.getBodyfatradio());
            foundReport.setBodymassindex(newReport.getBodymassindex());
            foundReport.setMusclemass(newReport.getMusclemass());
            reportRepository.save(foundReport);
            return foundReport;
        }else
            return null;

    }

    public void deleteOneReport(Long reportId) {
        reportRepository.deleteById(reportId);
    }
}
