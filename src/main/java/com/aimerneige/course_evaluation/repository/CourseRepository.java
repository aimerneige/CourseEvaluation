package com.aimerneige.course_evaluation.repository;

import com.aimerneige.course_evaluation.entity.Course;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends CrudRepository<Course, Long> {
    Course findById(long id);

    Iterable<Course> findByTitle(String title);

    Iterable<Course> findByTeacherId(long teacherId);

    @Query(value = "select c.* from course c " +
        "inner join course_students cs on cs.course_id = c.id " +
        "inner join student s on cs.students_id = s.id " +
        "where s.id = :studentId", nativeQuery = true)
    List<Course> findByStudentId(@Param("studentId") long studentId);
}
