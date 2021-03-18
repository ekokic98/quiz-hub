package com.quizhub.property;

import com.quizhub.property.model.Person;
import com.quizhub.property.repositories.PersonRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.UUID;

@SpringBootApplication
public class PropertyApplication {

	public static void main(String[] args) {
		SpringApplication.run(PropertyApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(PersonRepository repository) {
		return (args) -> {
			//unos podataka
         if (!repository.existsByUsername("O'Brian")) {
			 repository.save(new Person(UUID.randomUUID(), "O'Brian"));
			 repository.save(new Person(UUID.randomUUID(), "Bauer"));
			 repository.save(new Person(UUID.randomUUID(), "Palmer"));
			 repository.save(new Person(UUID.randomUUID(), "Dessler"));
		 }
			System.out.println(repository.existsByUsername("Desslerov"));
		};
	}
}
