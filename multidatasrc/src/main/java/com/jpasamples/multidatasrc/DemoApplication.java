package com.jpasamples.multidatasrc;

import com.jpasamples.multidatasrc.dba.Employee;
import com.jpasamples.multidatasrc.dba.EmployeeRepo;
import com.jpasamples.multidatasrc.dbb.EmployeeB;
import com.jpasamples.multidatasrc.dbb.EmployeeBRepo;

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
	EmployeeRepo emprepo;

	@Autowired
	EmployeeBRepo emp2repo;

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub

		Employee emp =new Employee();
		emp.setId(1);
		emp.setName("Alex");

		emprepo.save(emp);

		EmployeeB emp1=new EmployeeB();

		emp1.setId(1);
		emp1.setName("Robert");
		emp2repo.save(emp1);



	}

}
