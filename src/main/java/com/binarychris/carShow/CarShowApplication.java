package com.binarychris.carShow;

import com.binarychris.carShow.entity.Car;
import com.binarychris.carShow.repository.CarRepository;
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
private static final Logger logger = LoggerFactory.getLogger(CarShowApplication.class);
	public static void main(String[] args) {

		SpringApplication.run(CarShowApplication.class, args);
		logger.info("Application started");

	}

	@Override
	public void run(String... args) throws Exception {
		// everything in here will run before the spring boot
		List<Car> cars = Arrays.asList(
				new Car("Ford", "Lightning", "Gray", "FL-234", 2023, 75000),
				new Car("Nissan", "Leaf", "Green", "BFG-345", 2022, 4000),
				new Car("Toyota", "Sienna", "Silver", "CDF-233", 2024, 6000),
				new Car("Honda", "Accord", "White", "HW-345", 2024, 57000)
				);
		carRepository.saveAll(cars);// adds all cars to database

		carRepository
				.findAll().forEach(car -> logger.info(car.getMake()+" "+car.getModel()));
	}			//.findAll() has access to .forEach() so we don't need .stream

	// ORM (Object Relational Mapping) : is a technique that allows you to fetch from and manipulate a database
	// by using OOP (Object Oriented Programming) paradigm. All relational databases use SQL. Can focus more on my logic now.
	// ORM is independent of database.
		// Hibernate : implementation of ORM. Most popular JPA implementation
		// JPA : Java Persistent API, provide orm for java
	// class Book (id, title, author, price) -> Table Book (id, title, author, price)
	// entity -> class that becomes a table
}
