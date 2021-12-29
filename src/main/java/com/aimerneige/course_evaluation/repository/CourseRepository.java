package com.aimerneige.course_evaluation.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.aimerneige.course_evaluation.model.Course;

@Repository
public interface CourseRepository extends CrudRepository<Course, Long> {
    Course findById(long id);

    Iterable<Course> findByTitle(String title);
}
