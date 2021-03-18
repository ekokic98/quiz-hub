package com.quizhub.property;

import com.quizhub.property.model.Person;
import com.quizhub.property.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PropertyApplication {

	public static void main(String[] args) {
		SpringApplication.run(PropertyApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(UserRepository repository) {
		return (args) -> {
			// save a few customers
			Person n = new Person();
			n.setUsername("Bauer");

			repository.save(n);
			//repository.save(new User(UUID.randomUUID(), "O'Brian"));
			//repository.save(new User(UUID.randomUUID(), "Bauer"));
			//repository.save(new User(UUID.randomUUID(), "Palmer"));
			//repository.save(new User(UUID.randomUUID(), "Dessler"));
		};
	}
}
