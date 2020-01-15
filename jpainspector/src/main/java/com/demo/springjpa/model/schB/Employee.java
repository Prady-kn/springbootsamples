package com.demo.springjpa.model.schB;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;



@Table(name = "emptab",  schema = "${schema.two}")
@Entity(name = "EmployeeB")
public class Employee{

String name;
@Id
Integer id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }



}