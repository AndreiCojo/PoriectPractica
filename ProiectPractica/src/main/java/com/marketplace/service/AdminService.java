package com.marketplace.service;

import com.marketplace.dto.AdminDto;
import com.marketplace.model.Admin;
import com.marketplace.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    public List<AdminDto> getAll() {

        List<Admin> admins = adminRepository.findAll();
        List<AdminDto> adminDtoList = new ArrayList<>();
        for (Admin admin: admins) {
            AdminDto adminDto = new AdminDto(admin);
            adminDtoList.add(adminDto);
        }

        return adminDtoList;
    }

    public String addadmin(AdminDto adminDto) {
        Admin admin = new Admin(adminDto);
        AdminRepository.save(Admin);
        return "Ok";
    }
}
