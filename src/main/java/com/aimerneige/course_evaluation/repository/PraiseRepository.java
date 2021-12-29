package com.aimerneige.course_evaluation.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.aimerneige.course_evaluation.model.Praise;

@Repository
public interface PraiseRepository extends CrudRepository<Praise, Long> {
    Praise findById(long id);
}
