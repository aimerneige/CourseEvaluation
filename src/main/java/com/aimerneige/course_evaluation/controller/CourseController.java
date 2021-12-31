package com.aimerneige.course_evaluation.controller;

import java.util.List;
import java.util.ArrayList;

import com.aimerneige.course_evaluation.dto.CourseDto;
import com.aimerneige.course_evaluation.model.Course;
import com.aimerneige.course_evaluation.model.Student;
import com.aimerneige.course_evaluation.model.Teacher;
import com.aimerneige.course_evaluation.param.CourseParam;
import com.aimerneige.course_evaluation.repository.CourseRepository;
import com.aimerneige.course_evaluation.repository.StudentRepository;
import com.aimerneige.course_evaluation.repository.TeacherRepository;
import com.aimerneige.course_evaluation.response.Response;

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
@RequestMapping("/course")
public class CourseController {

    private final CourseRepository repository;
    private final TeacherRepository teacherRepository;
    private final StudentRepository studentRepository;

    private final Response courseNotFoundResponse = Response.notFound("Course not found");
    private final Response teacherNotFoundResponse = Response.notFound("Teacher not found");
    private final Response studentNotFoundResponse = Response.notFound("Student not found");

    @Autowired
    public CourseController(CourseRepository repository, TeacherRepository teacherRepository,
            StudentRepository studentRepository) {
        this.repository = repository;
        this.teacherRepository = teacherRepository;
        this.studentRepository = studentRepository;
    }

    @GetMapping("")
    public Response getAllCourses() {
        Iterable<Course> courses = repository.findAll();
        List<CourseDto> dtos = new ArrayList<>();
        for (Course course : courses) {
            dtos.add(new CourseDto(course));
        }
        if (dtos.isEmpty()) {
            return courseNotFoundResponse;
        }
        return Response.success(dtos);
    }

    @PostMapping("")
    public Response createCourse(@RequestBody CourseParam param) {
        // check if teacher exists
        long teacherId = param.getTeacherId();
        Teacher teacher = teacherRepository.findById(teacherId);
        if (teacher == null) {
            return teacherNotFoundResponse;
        }
        // save course
        Course course = new Course();
        course.setTitle(param.getTitle());
        course.setDescription(param.getDescription());
        course.setTeacher(teacher);
        repository.save(course);
        return Response.success(new CourseDto(course));
    }

    @GetMapping("/{id}")
    public Response getCourseById(@PathVariable("id") long id) {
        Course course = repository.findById(id);
        if (course == null) {
            return courseNotFoundResponse;
        }
        return Response.success(new CourseDto(course));
    }

    @PutMapping("/{id}")
    public Response updateCourse(@PathVariable("id") long id, @RequestBody CourseParam param) {
        Course course = repository.findById(id);
        if (course == null) {
            return courseNotFoundResponse;
        }
        // check if teacher exists
        long teacherId = param.getTeacherId();
        Teacher teacher = teacherRepository.findById(teacherId);
        if (teacher == null) {
            return teacherNotFoundResponse;
        }
        // update course
        course.setTitle(param.getTitle());
        course.setDescription(param.getDescription());
        course.setTeacher(teacher);
        repository.save(course);
        return Response.success(new CourseDto(course));
    }

    @DeleteMapping("/{id}")
    public Response deleteCourse(@PathVariable("id") long id) {
        Course course = repository.findById(id);
        if (course == null) {
            return courseNotFoundResponse;
        }
        repository.delete(course);
        return Response.success();
    }

    @GetMapping("/search")
    public Response searchCourse(String title) {
        Iterable<Course> courses = repository.findByTitle(title);
        List<CourseDto> dtos = new ArrayList<>();
        for (Course course : courses) {
            dtos.add(new CourseDto(course));
        }
        if (dtos.isEmpty()) {
            return courseNotFoundResponse;
        }
        return Response.success(dtos);
    }

    @PostMapping("/{id}/student")
    public Response insertCourseStudents(@PathVariable("id") long id, @RequestParam long studentId) {
        Course course = repository.findById(id);
        if (course == null) {
            return teacherNotFoundResponse;
        }
        Student student = studentRepository.findById(studentId);
        if (student == null) {
            return studentNotFoundResponse;
        }
        course.students.add(student);
        repository.save(course);
        return Response.success(new CourseDto(course));
    }

    @DeleteMapping("/{id}/student")
    public Response deleteCourseStudent(@PathVariable("id") long id, @RequestParam long studentId) {
        Course course = repository.findById(id);
        if (course == null) {
            return teacherNotFoundResponse;
        }
        Student student = studentRepository.findById(studentId);
        if (student == null) {
            return studentNotFoundResponse;
        }
        course.students.remove(student);
        repository.save(course);
        return Response.success(new CourseDto(course));
    }
}
