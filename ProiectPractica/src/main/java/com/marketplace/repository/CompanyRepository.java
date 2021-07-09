package com.marketplace.repository;

import com.marketplace.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CompanyRepository extends JpaRepository<Company, Long>,
CompanyRepositoryCustom {
    List<Company> findByName(String name);
}
