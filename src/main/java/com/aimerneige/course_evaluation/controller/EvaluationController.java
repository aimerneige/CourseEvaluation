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
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/evaluation")
public class EvaluationController {

    private final EvaluationRepository repository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    private final Response evaluationNotFoundResponse = Response.notFound("Evaluation not found");
    private final Response studentNotFoundResponse = Response.notFound("Student not found");
    private final Response courseNotFoundResponse = Response.notFound("Course not found");
    private final Response studentNotInCourseResponse = Response.badRequest("Student not in course");
    private final Response studentAlreadyEvaluatedResponse = Response
            .badRequest("Student has already evaluated this course");

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
        // check if student in course
        if (!course.getStudents().contains(student)) {
            return studentNotInCourseResponse;
        }
        // check if student has already evaluated this course
        if (repository.findByStudentIdAndCourseId(studentId, courseId) != null) {
            return studentAlreadyEvaluatedResponse;
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
        // check if student in course
        if (!course.getStudents().contains(student)) {
            return studentNotInCourseResponse;
        }
        // check if student has already evaluated this course
        if (repository.findByStudentIdAndCourseId(studentId, courseId) != null) {
            return studentAlreadyEvaluatedResponse;
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

    @GetMapping("/student")
    public Response getAllEvaluationByStudentId(@RequestParam long studentId) {
        Iterable<Evaluation> evaluations = repository.findByStudentId(studentId);
        List<EvaluationDto> dtos = new ArrayList<>();
        for (Evaluation evaluation : evaluations) {
            dtos.add(new EvaluationDto(evaluation));
        }
        if (dtos.isEmpty()) {
            return evaluationNotFoundResponse;
        }
        return Response.success(dtos);
    }

    @GetMapping("/course")
    public Response getAllEvaluationByCourseId(@RequestParam long courseId) {
        Iterable<Evaluation> evaluations = repository.findByCourseId(courseId);
        List<EvaluationDto> dtos = new ArrayList<>();
        for (Evaluation evaluation : evaluations) {
            dtos.add(new EvaluationDto(evaluation));
        }
        if (dtos.isEmpty()) {
            return evaluationNotFoundResponse;
        }
        return Response.success(dtos);
    }
}
