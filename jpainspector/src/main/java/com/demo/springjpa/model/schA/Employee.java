package com.demo.springjpa.model.schA;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Value;



@Table(name = "emptab", schema = "${schema.one}")
@Entity(name = "EmployeeA")
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