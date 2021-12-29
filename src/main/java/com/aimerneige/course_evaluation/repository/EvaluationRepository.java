package com.aimerneige.course_evaluation.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.aimerneige.course_evaluation.model.Evaluation;

@Repository
public interface EvaluationRepository extends CrudRepository<Evaluation, Long> {
    Evaluation findById(long id);
}
