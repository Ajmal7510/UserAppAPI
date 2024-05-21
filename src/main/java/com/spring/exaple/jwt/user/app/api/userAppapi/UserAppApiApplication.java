package com.spring.exaple.jwt.user.app.api.userAppapi;

import com.spring.exaple.jwt.user.app.api.userAppapi.Entity.Role;
import com.spring.exaple.jwt.user.app.api.userAppapi.Entity.User;
import com.spring.exaple.jwt.user.app.api.userAppapi.Repository.RoleRepository;
import com.spring.exaple.jwt.user.app.api.userAppapi.Repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class UserAppApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserAppApiApplication.class, args);
	}


	@Bean
	CommandLineRunner run(RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder bCryptPasswordEncoder) {
		return args -> {
			if (roleRepository.findByAuthority("ADMIN").isPresent()) return;
			Role adminRole = roleRepository.save(new Role("ADMIN"));
			roleRepository.save(new Role("USER"));

			Set<Role> roles = new HashSet<>();
			roles.add(adminRole);
			User admin = new User(1L,"admin","admin","admin@gmail.com", bCryptPasswordEncoder.encode("password"),"",true,roles );

			userRepository.save(admin);

		};
	}


	@Bean
	public WebMvcConfigurer corsConfigures() {
		return new WebMvcConfigurer() {
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
						.allowedMethods("*")
						.allowedOrigins("http://localhost:3000");
			}

		};
	}

}
