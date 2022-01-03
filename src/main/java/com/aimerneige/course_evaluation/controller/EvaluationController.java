package com.aimerneige.course_evaluation.controller;

import java.util.ArrayList;
import java.util.List;

import com.aimerneige.course_evaluation.dto.EvaluationDto;
import com.aimerneige.course_evaluation.entity.Course;
import com.aimerneige.course_evaluation.entity.Evaluation;
import com.aimerneige.course_evaluation.entity.Student;
import com.aimerneige.course_evaluation.param.EvaluationParam;
import com.aimerneige.course_evaluation.repository.CourseRepository;
import com.aimerneige.course_evaluation.repository.EvaluationRepository;
import com.aimerneige.course_evaluation.repository.StudentRepository;
import com.aimerneige.course_evaluation.response.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/evaluation")
public class EvaluationController {

    private final EvaluationRepository repository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    private final Response evaluationNotFoundResponse = Response.notFound("Evaluation not found");
    private final Response studentNotFoundResponse = Response.notFound("Student not found");
    private final Response courseNotFoundResponse = Response.notFound("Course not found");

    @Autowired
    public EvaluationController(EvaluationRepository repository, StudentRepository studentRepository,
            CourseRepository courseRepository) {
        this.repository = repository;
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;

    }

    @GetMapping("")
    public Response getAll() {
        Iterable<Evaluation> evaluations = repository.findAll();
        List<EvaluationDto> dtos = new ArrayList<>();
        for (Evaluation evaluation : evaluations) {
            dtos.add(new EvaluationDto(evaluation));
        }
        if (dtos.isEmpty()) {
            return evaluationNotFoundResponse;
        }
        return Response.success(dtos);
    }

    @PostMapping("")
    public Response createEvaluation(@RequestBody EvaluationParam param) {
        // check if student exist
        long studentId = param.getStudentId();
        Student student = studentRepository.findById(studentId);
        if (student == null) {
            return studentNotFoundResponse;
        }
        // check if course exist
        long courseId = param.getCourseId();
        Course course = courseRepository.findById(courseId);
        if (course == null) {
            return courseNotFoundResponse;
        }
        // create evaluation
        Evaluation evaluation = new Evaluation();
        evaluation.setStudent(student);
        evaluation.setCourse(course);
        repository.save(evaluation);
        return Response.success(new EvaluationDto(evaluation));
    }

    @GetMapping("/{id}")
    public Response getEvaluationById(@PathVariable("id") long id) {
        Evaluation evaluation = repository.findById(id);
        if (evaluation == null) {
            return evaluationNotFoundResponse;
        }
        return Response.success(new EvaluationDto(evaluation));
    }

    @PutMapping("/{id}")
    public Response updateEvaluation(@PathVariable("id") long id, @RequestBody EvaluationParam param) {
        Evaluation evaluation = repository.findById(id);
        if (evaluation == null) {
            return evaluationNotFoundResponse;
        }
        // check if student exist
        long studentId = param.getStudentId();
        Student student = studentRepository.findById(studentId);
        if (student == null) {
            return studentNotFoundResponse;
        }
        // check if course exist
        long courseId = param.getCourseId();
        Course course = courseRepository.findById(courseId);
        if (course == null) {
            return courseNotFoundResponse;
        }
        // update evaluation
        evaluation.setStudent(student);
        evaluation.setCourse(course);
        repository.save(evaluation);
        return Response.success(new EvaluationDto(evaluation));
    }

    @DeleteMapping("/{id}")
    public Response deleteEvaluation(@PathVariable("id") long id) {
        Evaluation evaluation = repository.findById(id);
        if (evaluation == null) {
            return evaluationNotFoundResponse;
        }
        repository.delete(evaluation);
        return Response.success();
    }
}
