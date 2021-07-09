package com.marketplace.service;

import com.marketplace.dto.AuthDto;
import com.marketplace.repository.AdminRepository;
import com.marketplace.security.UserPrinciple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    AdminRepository adminRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Optional<AuthDto> optionalAuthDto = adminRepository.getByEmailWithPasswordAndRole(email);
//        Optional<Admin> optionalAdmin = adminRepository.findByEmail(email);

        if (optionalAuthDto.isPresent()) {
            return UserPrinciple.build(optionalAuthDto.get());
        } throw new UsernameNotFoundException("No User found with email: " + email);

    }
}
