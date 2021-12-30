package com.aimerneige.course_evaluation.controller;

import com.aimerneige.course_evaluation.dto.StudentDto;
import com.aimerneige.course_evaluation.model.Student;
import com.aimerneige.course_evaluation.repository.StudentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentRepository repository;

    @Autowired
    public StudentController(StudentRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/{id}")
    public @ResponseBody StudentDto getStudentById(@PathVariable("id") long id) {
        Student student = repository.findById(id);
        return new StudentDto(student);
    }
}
