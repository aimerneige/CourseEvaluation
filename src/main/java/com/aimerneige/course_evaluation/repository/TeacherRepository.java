package com.aimerneige.course_evaluation.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;

import com.aimerneige.course_evaluation.model.Teacher;

@Repository
public interface TeacherRepository extends CrudRepository<Teacher, Long> {
    Teacher findById(long id);

    Iterable<Teacher> findByName(String name);
}
