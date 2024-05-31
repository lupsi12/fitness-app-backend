package com.example.demo.services;

import com.example.demo.entities.admin;
import com.example.demo.entities.user;
import com.example.demo.repos.AdminRepository;
import com.example.demo.requests.AdminCreateRequest;
import com.example.demo.requests.AdminUpdateRequest;
import com.example.demo.responses.adminResponses;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class adminService {
    private AdminRepository adminRepository;
    private userService userService;
    public adminService(AdminRepository adminRepository,userService userService) {
        this.adminRepository = adminRepository;
        this.userService = userService;
    }

    public List<adminResponses> getAllAdmin(Optional<Long> userId) {
        List<admin> list;
        if(userId.isPresent())
            list = adminRepository.findByUserId(userId.get());
        else
        list = adminRepository.findAll();
        return list.stream().map(p -> new adminResponses(p)).collect(Collectors.toList());
    }

    public admin saveOneAdmin(AdminCreateRequest newAdminRequest) {
        user user = userService.getOneUser(newAdminRequest.getUser_id());
        if(user == null)
            return null;
        admin toSave = new admin();
        toSave.setId(newAdminRequest.getId());
        toSave.setUser(user);
        return adminRepository.save(toSave);
    }

    public admin getOneAdmin(Long adminId) {
        return adminRepository.findById(adminId).orElse(null);
    }

    public admin updateOneAdmin(Long adminId, AdminUpdateRequest adminUpdateRequest) {
        Optional<admin> admin = adminRepository.findById(adminId);
        if(admin.isPresent()){
            admin foundAdmin = admin.get();
            //foundAdmin.setUser(adminUpdateRequest.blabla);
            adminRepository.save(foundAdmin);
            return foundAdmin;
        }
        else
            return null;
    }

    public void deleteOneAdmin(Long adminId) {
        adminRepository.deleteById(adminId);
    }
}
