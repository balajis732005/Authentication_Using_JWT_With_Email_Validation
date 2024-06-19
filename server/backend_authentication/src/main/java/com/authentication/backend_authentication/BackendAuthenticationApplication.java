package com.authentication.backend_authentication;

import com.authentication.backend_authentication.role.Role;
import com.authentication.backend_authentication.role.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableJpaAuditing // For last modified and created data time in Entity
@EnableAsync // For Send email in Async way to avoid more time for that
public class BackendAuthenticationApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendAuthenticationApplication.class, args);
	}

//	@Bean
//	public CommandLineRunner runner(RoleRepository roleRepository){
//		return args -> {
//			if(roleRepository.findByName("USER").isEmpty()){
//				roleRepository.save(
//						Role.builder().name("USER").build()
//				);
//			}
//		};
//	}

}
