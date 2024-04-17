package com.java.studentmgmt.controller;

import com.java.studentmgmt.model.Student;
import com.java.studentmgmt.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping("/student")
    public ResponseEntity<Student> addStudent(@RequestBody Student student) {
        Student newStudent = studentService.createStudent(student);
        if (newStudent != null) {
            return new ResponseEntity<>(newStudent, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/students")
    public ResponseEntity<Page<Student>> getAllStudents(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        Page<Student> students = studentService.getAllStudents(pageable);

        if (students.hasContent()) {
            return new ResponseEntity<>(students, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/students/sorted")
    public ResponseEntity<Page<Student>> getAllStudentsSorted(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy) {

        Pageable pageable = PageRequest.of(page, size);

        Page<Student> students = studentService.getAllStudentsSorted(sortBy, pageable);
        if (students.hasContent()) {
            return new ResponseEntity<>(students, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/student/{studentId}")
    public ResponseEntity<Student> updateStudent(@PathVariable int studentId, @RequestBody Student student) {
        boolean updated = studentService.updateStudent(studentId, student);
        if (updated) {
            return new ResponseEntity<>(student, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/student/{studentId}")
    public ResponseEntity<Boolean> deleteStudent(@PathVariable int studentId) {
        boolean deleted = studentService.deleteStudent(studentId);
        if (deleted) {
            return new ResponseEntity<>(true, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
        }
    }
}
