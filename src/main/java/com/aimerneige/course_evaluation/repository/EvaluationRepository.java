package com.aimerneige.course_evaluation.repository;

import com.aimerneige.course_evaluation.entity.Evaluation;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EvaluationRepository extends CrudRepository<Evaluation, Long> {
    Evaluation findById(long id);

    Evaluation findByStudentIdAndCourseId(long studentId, long courseId);
}
