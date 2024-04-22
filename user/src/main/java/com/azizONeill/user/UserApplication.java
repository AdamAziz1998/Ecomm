package com.azizONeill.user;

import com.azizONeill.user.model.User;
import com.azizONeill.user.model.enums.Role;
import com.azizONeill.user.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class UserApplication implements CommandLineRunner {

	private final UserRepository userRepository;

	public UserApplication(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(UserApplication.class, args);
	}

	@Override
	public void run(String... args) {
		final String pass = "$2a$10$2529eBq3R6Y41t03Mku2I.2Nh3W0p25lt.s.85mG0kiAvxI4bsAHa";
		var admin = User.builder()
				.email("admin@gmail.com")
				.password(pass)
				.role(Role.ADMIN).build();
		if (userRepository.findByEmail("admin@gmail.com").isEmpty()) userRepository.save(admin);
	}

}
