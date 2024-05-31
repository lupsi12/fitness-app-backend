package com.example.demo.controllers;

import com.example.demo.entities.admin;
import com.example.demo.repos.AdminRepository;
import com.example.demo.requests.AdminCreateRequest;
import com.example.demo.requests.AdminUpdateRequest;
import com.example.demo.responses.adminResponses;
import com.example.demo.services.adminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin")
public class adminController {
    @Autowired
    private adminService adminService;

    public adminController(adminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping
    public List<adminResponses> getAllAdmin(@RequestParam Optional<Long> userId){
        return adminService.getAllAdmin(userId);
    }
    @PostMapping
    public  admin createAdmin(@RequestBody AdminCreateRequest newAdminRequest){
        return adminService.saveOneAdmin(newAdminRequest);
    }
    @GetMapping("/{adminId}")
    public  admin getOneAdmin(@PathVariable Long adminId){
        return adminService.getOneAdmin(adminId);
    }

    @PutMapping("/{adminId}")
    public admin updateOneAdmin(@PathVariable Long adminId , @RequestBody AdminUpdateRequest adminUpdateRequest){
        return adminService.updateOneAdmin(adminId,adminUpdateRequest);
    }
    @DeleteMapping("/{adminId}")
    public void deleteOneAdmin(@PathVariable Long adminId){
        adminService.deleteOneAdmin(adminId);
    }
}
