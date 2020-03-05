package com.example.sqlserver;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "person_from_tab")
public class PersonEntity {
    
    @Id
    Integer id;
    
    String name;

    Date dtcreatedon;

}