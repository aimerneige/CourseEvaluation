package com.aimerneige.course_evaluation.controller;

import com.aimerneige.course_evaluation.dto.CourseDto;
import com.aimerneige.course_evaluation.model.Course;
import com.aimerneige.course_evaluation.repository.CourseRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/course")
public class CourseController {

    private final CourseRepository repository;

    @Autowired
    public CourseController(CourseRepository repository) {
        this.repository = repository;
    }

    @ApiOperation(value = "Get courses by id", notes = "Get courses by id")
    @GetMapping("/{id}")
    public @ResponseBody CourseDto getCourseById(@PathVariable("id") long id) {
        Course course = repository.findById(id);
        return new CourseDto(course);
    }
}
