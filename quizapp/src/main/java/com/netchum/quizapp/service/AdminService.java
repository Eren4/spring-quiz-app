package com.netchum.quizapp.service;

import com.netchum.quizapp.entity.Admin;
import com.netchum.quizapp.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    private final AdminRepository adminRepository;

    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    public Optional<Admin> getAdminById(int id) {
        return adminRepository.findById(id);
    }

    public Admin getAdminByEmail(String email) {
        return adminRepository.findByEmail(email);
    }

    public boolean authenticateAdmin(String email, String password) {
        Admin admin = adminRepository.findByEmail(email);

        if(admin != null && passwordEncoder.matches(password, admin.getPassword())) {
            return true;
        }

        return false;
    }
}
