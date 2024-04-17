package com.java.studentmgmt.service;

import com.java.studentmgmt.model.Student;
import com.java.studentmgmt.repository.StudentRepo;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepo studentRepo;

    public Student createStudent(@NonNull Student student) {
        return studentRepo.save(student);
    }

    public List<Student> getAllStudents() {
        return studentRepo.findAll();
    }

    public Student getStudentById(@NonNull Integer id) {
        return studentRepo.findById(id).orElse(null);
    }

    public boolean updateStudent(int id, Student student) {
        if (getStudentById(id) == null) {
            return false;
        }
        try {
            studentRepo.save(student);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public boolean deleteStudent(int id) {
        if (getStudentById(id) == null) {
            return false;
        }
        studentRepo.deleteById(id);
        return true;
    }

    public Page<Student> getAllStudents(Pageable pageable) {
        return studentRepo.findAll(pageable);
    }

    public Page<Student> getAllStudentsSorted(String sortBy, Pageable pageable) {
        Sort sort = Sort.by(sortBy);
        pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);
        return studentRepo.findAll(pageable);
    }
}
