package com.tableinsql.demo.controller;


import com.tableinsql.demo.model.Student;
import com.tableinsql.demo.service.StudentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    @Autowired
    private StudentsService service;
    @GetMapping
    public List<Student> getAllStudents() {
        return service.getAllStudents();
    }
    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable Long id) {
        return service.getStudentById(id);
    }
    @PutMapping("/{id}")
    public Student updateStudentById(@PathVariable Long id, @RequestBody Student student) {
        return service.updateStudentById(id,student);
    }
    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        return service.createStudent(student);
    }
    @DeleteMapping("/{id}")
    public String deleteStudentById(@PathVariable Long id) {
        return service.deleteStudentById(id);
    }


}
