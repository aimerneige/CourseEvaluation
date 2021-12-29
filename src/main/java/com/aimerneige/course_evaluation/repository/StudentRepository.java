package com.aimerneige.course_evaluation.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.aimerneige.course_evaluation.model.Student;

@Repository
public interface StudentRepository extends CrudRepository<Student, Long> {
    Student findById(long id);

    Iterable<Student> findByName(String name);
}
