package com.demo.springjpa.model.schB.repo;

import java.util.List;

import com.demo.springjpa.model.schB.Employee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepoB extends JpaRepository<Employee,Integer> {

   @Query(value = "select * from ${schema_two}.emptab",nativeQuery = true)
   public List<Employee> findUsingNativeQuery();

   @Query(value = "select e from EmployeeB e")
   public List<Employee> findUsingJPQL();

}