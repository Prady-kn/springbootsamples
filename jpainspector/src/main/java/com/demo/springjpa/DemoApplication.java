package com.demo.springjpa;

import java.util.List;

import com.demo.springjpa.model.schA.Employee;
import com.demo.springjpa.model.schA.repo.EmployeeRepo;
import com.demo.springjpa.model.schB.repo.EmployeeRepoB;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.demo.springjpa"})
public class DemoApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Autowired
	EmployeeRepo emprepoA;


	@Autowired
	EmployeeRepoB emprepoB;

	// @Autowired
	// SpringContextUtil sCtx;

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		Employee emp1=new Employee();
		emp1.setId(1);
		emp1.setName("Alex");
		emprepoA.save(emp1);


		com.demo.springjpa.model.schB.Employee emp2=new com.demo.springjpa.model.schB.Employee();
		emp2.setId(2);
		emp2.setName("Rob");
		emprepoB.save(emp2);

		List<com.demo.springjpa.model.schB.Employee> allemp=emprepoB.findUsingJPQL();
		Integer cc=allemp.size();

		allemp=emprepoB.findUsingNativeQuery();

		 cc=allemp.size();

		 allemp=emprepoB.findUsingNativeQuery();

		 cc=allemp.size();

		 allemp=emprepoB.findAll();
	}

}
