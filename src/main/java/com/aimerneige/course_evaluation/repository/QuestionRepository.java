package com.aimerneige.course_evaluation.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.aimerneige.course_evaluation.model.Question;

@Repository
public interface QuestionRepository extends CrudRepository<Question, Long> {
    Question findById(long id);
}
