package com.aimerneige.course_evaluation.repository;

import com.aimerneige.course_evaluation.entity.Course;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends CrudRepository<Course, Long> {
    Course findById(long id);

    Iterable<Course> findByTitle(String title);

    Iterable<Course> findByTeacherId(long teacherId);
}
