package com.aimerneige.course_evaluation.controller;

import com.aimerneige.course_evaluation.dto.TeacherDto;
import com.aimerneige.course_evaluation.model.Teacher;
import com.aimerneige.course_evaluation.repository.TeacherRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/teacher")
public class TeacherController {

    private final TeacherRepository repository;

    @Autowired
    public TeacherController(TeacherRepository repository) {
        this.repository = repository;
    }

    @ApiOperation(value = "Get teacher by id", notes = "Get teacher by id")
    @GetMapping("/{id}")
    public @ResponseBody TeacherDto getTeacherById(@PathVariable("id") long id) {
        Teacher teacher = repository.findById(id);
        return new TeacherDto(teacher);
    }
}
