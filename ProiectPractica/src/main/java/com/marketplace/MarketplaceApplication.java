package com.marketplace;

import com.marketplace.repository.AdminRepository;
import com.marketplace.repository.HobbyRepository;
import com.marketplace.repository.RoleRepository;
import com.marketplace.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MarketplaceApplication implements CommandLineRunner {

	@Autowired
	HobbyRepository hobbyRepository;

	@Autowired
	AdminRepository adminRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	EmailService emailService;

	public static void main(String[] args) {
		SpringApplication.run(MarketplaceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		//emailService.sendMail("george.brande@cst.ro", "test subject",
				//"test text");

//		Optional<admin> optionaladmin = adminRepository.findByEmail("gxg@cst.ro");
//		if (optionaladmin.isPresent()) {
//			admin admin = optionaladmin.get();
//
//			admin.setPassword(BCrypt.hashpw("Password2", BCrypt.gensalt()));
//
//			adminRepository.save(admin);
//		}


//		Role admin = new Role("ROLE_ADMIN");
//		Role roleDefault = new Role("ROLE_DEFAULT");
//
//		roleRepository.save(admin);
//		roleRepository.save(roleDefault);

//		Optional<admin> optionaladmin =
//				adminRepository.findById(1L);
//
//		if (optionaladmin.isPresent()) {
//			admin admin = optionaladmin.get();
//
//			Optional<Role> optionalRole = roleRepository.findByRole("ROLE_ADMIN");
//			if (optionalRole.isPresent()) {
//				Role role = optionalRole.get();
//
//				admin.setRole(role);
//				adminRepository.save(admin);
//			}
//
//		}
//
//		admin defaultadmin = new admin("gxg@cst.ro", "George", 18);
//		adminRepository.save(defaultadmin);
//
//		Optional<Role> optionalRole = roleRepository.findByRole("ROLE_DEFAULT");
//		if (optionalRole.isPresent()) {
//			Role role = optionalRole.get();
//
//			defaultadmin.setRole(role);
//			adminRepository.save(defaultadmin);
//		}

	}

}
