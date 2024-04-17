package com.java.studentmgmt.model;

import lombok.Data;
import lombok.NoArgsConstructor;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Data
@Entity
@NoArgsConstructor
public class StudentDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "detail")
    private String detail;

    @OneToOne
    @JoinColumn(name = "student_id")
    private Student student;

    public StudentDetail(String detail) {
        this.detail = detail;
    }
}
