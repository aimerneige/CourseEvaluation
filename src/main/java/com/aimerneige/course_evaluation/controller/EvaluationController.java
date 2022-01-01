package com.aimerneige.course_evaluation.controller;

import com.aimerneige.course_evaluation.dto.EvaluationDto;
import com.aimerneige.course_evaluation.entity.Evaluation;
import com.aimerneige.course_evaluation.repository.EvaluationRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/evaluation")
public class EvaluationController {

    private final EvaluationRepository repository;

    @Autowired
    public EvaluationController(EvaluationRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/{id}")
    public @ResponseBody EvaluationDto getEvaluationById(@PathVariable("id") long id) {
        Evaluation evaluation = repository.findById(id);
        return new EvaluationDto(evaluation);
    }
}
