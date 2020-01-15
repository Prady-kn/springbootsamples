package com.demo.springjpa.model.schA.repo;

import com.demo.springjpa.model.schA.Employee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee,Integer> {


}