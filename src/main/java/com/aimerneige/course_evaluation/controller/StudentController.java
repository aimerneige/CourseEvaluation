package com.aimerneige.course_evaluation.controller;

import java.util.ArrayList;
import java.util.List;

import com.aimerneige.course_evaluation.dto.StudentDto;
import com.aimerneige.course_evaluation.entity.Student;
import com.aimerneige.course_evaluation.param.StudentParam;
import com.aimerneige.course_evaluation.repository.StudentRepository;
import com.aimerneige.course_evaluation.response.Response;
import com.aimerneige.course_evaluation.utils.HashUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentRepository repository;

    private final Response studentNotFoundResponse = Response.notFound("Student not found");
    private final Response studentIdNumberExistResponse = Response.badRequest("Student id number already exists");

    @Autowired
    public StudentController(StudentRepository repository) {
        this.repository = repository;
    }

    @GetMapping("")
    public Response getAllStudent() {
        Iterable<Student> students = repository.findAll();
        List<StudentDto> dtos = new ArrayList<>();
        for (Student student : students) {
            dtos.add(new StudentDto(student));
        }
        if (dtos.isEmpty()) {
            return studentNotFoundResponse;
        }
        return Response.success(dtos);
    }

    @PostMapping("")
    public Response createStudent(@RequestBody StudentParam param) {
        // check if idNumber exist
        if (repository.findByIdNumber(param.getIdNumber()) != null) {
            return studentIdNumberExistResponse;
        }
        // save student
        Student student = new Student();
        student.setIdNumber(param.getIdNumber());
        student.setName(param.getName());
        student.setPhone(param.getPhone());
        student.setSex(param.getSex());
        student.setEmail(param.getEmail());
        student.setPassword(HashUtils.md5(param.getPassword()));
        student.setAge(param.getAge());
        repository.save(student);
        return Response.success(new StudentDto(student));
    }

    @GetMapping("/{id}")
    public Response getStudentById(@PathVariable("id") long id) {
        Student student = repository.findById(id);
        if (student == null) {
            return studentNotFoundResponse;
        }
        return Response.success(new StudentDto(student));
    }

    @PutMapping("/{id}")
    public Response updateStudent(@PathVariable("id") long id, @RequestBody StudentParam param) {
        Student student = repository.findById(id);
        if (student == null) {
            return studentNotFoundResponse;
        }
        // check if idNumber exist
        if (repository.findByIdNumber(param.getIdNumber()) != null
                && !student.getIdNumber().equals(param.getIdNumber())) {
            return studentIdNumberExistResponse;
        }
        // update student
        student.setIdNumber(param.getIdNumber());
        student.setName(param.getName());
        student.setPhone(param.getPhone());
        student.setSex(param.getSex());
        student.setEmail(param.getEmail());
        student.setPassword(HashUtils.md5(param.getPassword()));
        student.setAge(param.getAge());
        repository.save(student);
        return Response.success(new StudentDto(student));
    }

    @DeleteMapping("/{id}")
    public Response deleteStudent(@PathVariable("id") long id) {
        Student student = repository.findById(id);
        if (student == null) {
            return studentNotFoundResponse;
        }
        repository.delete(student);
        return Response.success();
    }

    @GetMapping("/search")
    public Response searchStudentByName(@RequestParam String name) {
        Iterable<Student> students = repository.findByName(name);
        List<StudentDto> dtos = new ArrayList<>();
        for (Student student : students) {
            dtos.add(new StudentDto(student));
        }
        if (dtos.isEmpty()) {
            return studentNotFoundResponse;
        }
        return Response.success(dtos);
    }
}
