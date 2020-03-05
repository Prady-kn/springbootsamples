package com.example.sqlserver;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}


	@Autowired
	PersonRepo personRepo;

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub


		List<PersonEntity> personColl=	personRepo.findAll();

	}

}
