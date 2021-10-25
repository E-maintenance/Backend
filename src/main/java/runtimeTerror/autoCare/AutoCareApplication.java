package runtimeTerror.autoCare;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import runtimeTerror.autoCare.model.Role;
import runtimeTerror.autoCare.repository.RoleRepository;

@SpringBootApplication
public class AutoCareApplication {
	private static final Logger log = LoggerFactory.getLogger(AutoCareApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(AutoCareApplication.class, args);	}

	@Bean
	CommandLineRunner initDatabase(RoleRepository roleRepository) {
		return args -> {
			log.info("Preloading " + roleRepository.save(new Role("ADMIN")));
			log.info("Preloading " + roleRepository.save(new Role("CUSTOMER")));
			log.info("Preloading " + roleRepository.save(new Role("WORKSHOP")));
		};
	}
}

