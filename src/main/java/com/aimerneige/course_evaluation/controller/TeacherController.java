package com.aimerneige.course_evaluation.controller;

import com.aimerneige.course_evaluation.dto.TeacherDto;
import com.aimerneige.course_evaluation.model.Teacher;
import com.aimerneige.course_evaluation.repository.TeacherRepository;
import com.aimerneige.course_evaluation.response.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/teacher")
public class TeacherController {

    private final TeacherRepository repository;

    private final Response teacherNotFoundResponse = Response.notFound("Teacher not found");

    @Autowired
    public TeacherController(TeacherRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/{id}")
    public Response getTeacherById(@PathVariable("id") long id) {
        Teacher teacher = repository.findById(id);
        if (teacher == null) {
            return teacherNotFoundResponse;
        }
        return Response.success(new TeacherDto(teacher));
    }
}
