package com.marketplace.repository;

import com.marketplace.dto.AuthDto;
import com.marketplace.model.Admin;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class AdminImpl implements AdminCustom {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public Optional<AuthDto> getByEmailWithPasswordAndRole(String email) {

        String query = "select new com.marketplace.dto.AuthDto" +
                " (d.email, d.password, d.role.role) " +
                " from admin d " +
                " where d.email = :email ";

        AuthDto authDto =
                entityManager.createQuery(query, AuthDto.class)
                .setParameter("email", email)
                .getSingleResult();

        return Optional.of(authDto);
    }

    @Override
    public List<Admin> getPaginatedAdmins(int firstResults, int maxResults) {

        String query = "select d from admin d ";

        List<Admin> admins = entityManager.createQuery(query, Admin.class)
                .setFirstResult(firstResults)
                .setMaxResults(maxResults)
                .getResultList();

        return admins;
    }

    @Override
    public long getTotalAdmins() {

        String query = "select count(d) from Admin d ";

        long totalAdmins = entityManager.createQuery(query, Long.class)
                .getSingleResult();
        return totalAdmins;
    }
}
