package com.java.studentmgmt.model;

import lombok.NoArgsConstructor;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
@NoArgsConstructor
public class Student {
    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    

    public Student(Integer id, String name) {
        this.id = id;
        this.name = name;
    }



    public Integer getId() {
        return id;
    }



    public void setId(Integer id) {
        this.id = id;
    }



    public String getName() {
        return name;
    }



    public void setName(String name) {
        this.name = name;
    }

    
}
