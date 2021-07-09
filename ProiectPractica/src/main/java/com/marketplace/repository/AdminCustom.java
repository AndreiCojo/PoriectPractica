package com.marketplace.repository;

import com.marketplace.dto.AuthDto;
import com.marketplace.model.Admin;

import java.util.List;
import java.util.Optional;

public interface AdminCustom {
    Optional<AuthDto> getByEmailWithPasswordAndRole(String email);

    List<Admin> getPaginatedDoctors(int firstResults, int maxResults);
    long getTotalDoctors();
}
