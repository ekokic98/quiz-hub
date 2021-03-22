package com.quizhub.property;

import com.quizhub.property.model.*;
import com.quizhub.property.repositories.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class PropertyApplication {

	public static void main(String[] args) {
		SpringApplication.run(PropertyApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(PersonRepository personRepository, CommentRepository commentRepository,
								  FavoriteRepository favoriteRepository, QuizRepository quizRepository,
								  RatingRepository ratingRepository, ScoreRepository scoreRepository) {
		return (args) -> {
			Person p1 = new Person(null, "Bauer", null), p2=  new Person(null, "Palmer", null),
					p3 = new Person(null, "Dessler", null);
			Quiz q1 = new Quiz(null, p1, "RPR quiz",300, 5),
					q2 = new Quiz(null, p1, "DM quiz",600, 10),
					q3 = new Quiz(null, p3, "IM1 quiz",1000, 20),
					q4 = new Quiz(null, p2, "PWNT quiz",200, 3);
			LocalDateTime s = LocalDateTime.now();
			Comment c1 = new Comment(null, p2, q4, "Wow, nice quiz!", s,s), c2 = new Comment(null, p1, q1, "Easy ;)", s,s);
			Rating r1 = new Rating(null, p1, q1, 5), r2 = new Rating(null, p2, q2, 2);
			Favorite f1 = new Favorite(null, q1, p1), f2 = new Favorite(null, q2, p1), f3 = new Favorite(null, q3, p3);
			Score s1 = new Score(null, p1, q1, 120, 5, 15, s), s2 = new Score(null, p1, q2, 200, 7, 5, s),
					s3 = new Score(null, p3, q3, 300, 12, 20, s), s4 = new Score(null, p3, q3, 200, 10, 13, s);
			List<Person> persons = Arrays.asList(p1,p2,p3);
			List<Quiz> quizzes = Arrays.asList(q1,q2,q3,q4);
			List<Comment> comments = Arrays.asList(c1,c2);
			List<Rating> ratings = Arrays.asList(r1, r2);
			List<Favorite> favorites = Arrays.asList(f1,f2,f3);
			List<Score> scores = Arrays.asList(s1,s2,s3, s4);

			personRepository.saveAll(persons);
		    quizRepository.saveAll(quizzes);
			commentRepository.saveAll(comments);
			ratingRepository.saveAll(ratings);
			favoriteRepository.saveAll(favorites);
			scoreRepository.saveAll(scores);

		};
	}
}
