package com.aimerneige.course_evaluation.repository;

import com.aimerneige.course_evaluation.entity.Student;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends CrudRepository<Student, Long> {
    Student findById(long id);

    Iterable<Student> findByName(String name);

    Student findByIdNumber(String idNumber);
}
