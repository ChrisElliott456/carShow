package com.binarychris.carShow;

import com.binarychris.carShow.entity.Car;
import com.binarychris.carShow.entity.Owner;
import com.binarychris.carShow.entity.User;
import com.binarychris.carShow.exception.ApiError;
import com.binarychris.carShow.repository.CarRepository;
import com.binarychris.carShow.repository.OwnerRepository;
import com.binarychris.carShow.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;


//@EnableAutoConfiguration - This enables Spring Boot automatic configuration. Spring Boo twill automatically configure your project based on the dependencies.
// For example, if you have the spring-boot-starter-web dependency, Spring boot assumes that you are developing a web application and configures your application accordingly.
//@ComponentScan - This enables the Spring Boot component scan to find all the components in the application.
// Create bean automatically and can scan anything in the carShow package.
//@Configuration - This annotation is used to define a configuration class that provides beans to the Spring application context
@SpringBootApplication
public class CarShowApplication implements CommandLineRunner {
	@Autowired
	private CarRepository carRepository;
	@Autowired
	private OwnerRepository ownerRepository;
	@Autowired
	private UserRepository userRepository;
private static final Logger logger = LoggerFactory.getLogger(CarShowApplication.class);
	public static void main(String[] args) {

		SpringApplication.run(CarShowApplication.class, args);
		logger.info("Application started");

	}

	@Override
	public void run(String... args) throws Exception {
		// everything in here will run before the spring boot
		Owner owner1 = new Owner("John", "Doe");
		Owner owner2 = new Owner("Jack", "Smith");
		ownerRepository.save(owner1);
		ownerRepository.save(owner2);
		List<Car> cars = Arrays.asList(
				new Car("Ford", "Lightning", "Gray", "FL-234", 2023, 75000, owner1),
				new Car("Nissan", "Leaf", "Green", "BFG-345", 2022, 4000, owner2),
				new Car("Toyota", "Sienna", "Silver", "CDF-233", 2024, 6000, owner1),
				new Car("Honda", "Accord", "White", "HW-345", 2024, 57000, owner2)
				);
		carRepository.saveAll(cars);// adds all cars to database

		userRepository.save(new User("user", "$2y$10$/cZcg7DCb4vmCgufbUy3jemb7qsbzJy2Ht5Nn7JjZdtIDolcFDvIq", "USER"));
		userRepository.save(new User("admin", "$2y$10$C9vJrWAwWTQeo8cE1VJtCudoJKC.1yjZNqzzrnNHG.uH0lUd1nD/O", "ADMIN"));

		carRepository
				.findAll().forEach(car -> logger.info(car.getMake()+" "+car.getModel()));

		ownerRepository.findAll().forEach(ow -> logger.info(ow.getFirstName()));
	}			//.findAll() has access to .forEach() so we don't need .stream


	// ORM (Object Relational Mapping) : is a technique that allows you to fetch from and manipulate a database
	// by using OOP (Object Oriented Programming) paradigm. All relational databases use SQL. Can focus more on my logic now.
	// ORM is independent of database.
		// Hibernate : implementation of ORM. Most popular JPA implementation
		// JPA : Java Persistent API, provide orm for java
	// class Book (id, title, author, price) -> Table Book (id, title, author, price)
	// entity -> class that becomes a table
}
