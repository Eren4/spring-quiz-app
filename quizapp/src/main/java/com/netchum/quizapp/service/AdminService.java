package com.netchum.quizapp.service;

import com.netchum.quizapp.entity.Admin;
import com.netchum.quizapp.repository.AdminRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminService {

    private final AdminRepository adminRepository;

    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    public Optional<Admin> getAdminById(int id) {
        return adminRepository.findById(id);
    }
}
