package com.aimerneige.course_evaluation.repository;

import org.springframework.stereotype.Repository;

import com.aimerneige.course_evaluation.entity.Teacher;

import org.springframework.data.repository.CrudRepository;

@Repository
public interface TeacherRepository extends CrudRepository<Teacher, Long> {
    Teacher findById(long id);

    Iterable<Teacher> findByName(String name);

    Teacher findByIdNumber(String idNumber);
}
