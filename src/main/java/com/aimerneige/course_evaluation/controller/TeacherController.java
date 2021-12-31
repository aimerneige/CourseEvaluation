package com.aimerneige.course_evaluation.controller;

import java.util.ArrayList;
import java.util.List;

import com.aimerneige.course_evaluation.dto.TeacherDto;
import com.aimerneige.course_evaluation.model.Teacher;
import com.aimerneige.course_evaluation.param.TeacherParam;
import com.aimerneige.course_evaluation.repository.TeacherRepository;
import com.aimerneige.course_evaluation.response.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/teacher")
public class TeacherController {

    private final TeacherRepository repository;

    private final Response teacherNotFoundResponse = Response.notFound("Teacher not found");
    private final Response teacherIdNumberExistResponse = Response.badRequest("Teacher id number already exists");

    @Autowired
    public TeacherController(TeacherRepository repository) {
        this.repository = repository;
    }

    @GetMapping("")
    public Response getAllTeacher() {
        Iterable<Teacher> teachers = repository.findAll();
        List<TeacherDto> dtos = new ArrayList<>();
        for (Teacher teacher : teachers) {
            dtos.add(new TeacherDto(teacher));
        }
        if (dtos.isEmpty()) {
            return teacherNotFoundResponse;
        }
        return Response.success(dtos);
    }

    @PostMapping("")
    public Response createTeacher(@RequestBody TeacherParam param) {
        // check if idNumber exist
        if (repository.findByIdNumber(param.getIdNumber()) != null) {
            return teacherIdNumberExistResponse;
        }
        // save teacher
        Teacher teacher = new Teacher();
        teacher.setIdNumber(param.getIdNumber());
        teacher.setName(param.getName());
        teacher.setPhone(param.getPhone());
        teacher.setSex(param.getSex());
        teacher.setAge(param.getAge());
        repository.save(teacher);
        return Response.success(new TeacherDto(teacher));
    }

    @GetMapping("/{id}")
    public Response getTeacherById(@PathVariable("id") long id) {
        Teacher teacher = repository.findById(id);
        if (teacher == null) {
            return teacherNotFoundResponse;
        }
        return Response.success(new TeacherDto(teacher));
    }

    @PostMapping("/{id}")
    public Response updateTeacher(@PathVariable("id") long id, @RequestBody TeacherParam param) {
        Teacher teacher = repository.findById(id);
        if (teacher == null) {
            return teacherNotFoundResponse;
        }
        // check if idNumber exist
        if (repository.findByIdNumber(param.getIdNumber()) != null
                && !teacher.getIdNumber().equals(param.getIdNumber())) {
            return teacherIdNumberExistResponse;
        }
        // update teacher
        teacher.setIdNumber(param.getIdNumber());
        teacher.setName(param.getName());
        teacher.setPhone(param.getPhone());
        teacher.setSex(param.getSex());
        teacher.setAge(param.getAge());
        repository.save(teacher);
        return Response.success(new TeacherDto(teacher));
    }

    @DeleteMapping("/{id}")
    public Response deleteTeacher(@PathVariable("id") long id) {
        Teacher teacher = repository.findById(id);
        if (teacher == null) {
            return teacherNotFoundResponse;
        }
        repository.delete(teacher);
        return Response.success();
    }

    @GetMapping("/search")
    public Response searchTeacherByName(@RequestParam String name) {
        Iterable<Teacher> teachers = repository.findByName(name);
        List<TeacherDto> dtos = new ArrayList<>();
        for (Teacher teacher : teachers) {
            dtos.add(new TeacherDto(teacher));
        }
        if (dtos.isEmpty()) {
            return teacherNotFoundResponse;
        }
        return Response.success(dtos);
    }
}
