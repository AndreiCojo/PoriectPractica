package com.marketplace.security;

import com.marketplace.dto.AuthDto;
import com.marketplace.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.util.Optional;

public class CustomAuthenticationProvider extends DaoAuthenticationProvider {

//    private static List<String> userList =Arrays.asList("gxg@cst.ro");

    private AdminRepository adminRepository;

    @Autowired
    public void setServices(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Override
    public Authentication authenticate(Authentication authentication) {
        String email = authentication.getName().trim();
        String inputPassword = authentication.getCredentials().toString();


        Optional<AuthDto> optionalAuthDto = adminRepository.getByEmailWithPasswordAndRole(email);
//        Optional<Admin> optionalAdmin = adminRepository.findByEmail(email);

        if (optionalAuthDto.isPresent()) {

            AuthDto authDto = optionalAuthDto.get();
//            Admin admin = optionalAdmin.get();
            String dbPassword = authDto.getPassword();

            if (BCrypt.checkpw(inputPassword, dbPassword)) {
                UserDetails userDetails = UserPrinciple.build(authDto);
                Authentication newAuth = new UsernamePasswordAuthenticationToken(userDetails,
                        null, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(newAuth);

                return newAuth;
            } throw new BadCredentialsException("Wrong password");

        } return null;
    }
}
