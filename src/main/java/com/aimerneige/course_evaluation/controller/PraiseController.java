package com.aimerneige.course_evaluation.controller;

import com.aimerneige.course_evaluation.dto.PraiseDto;
import com.aimerneige.course_evaluation.entity.Praise;
import com.aimerneige.course_evaluation.repository.PraiseRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/praise")
public class PraiseController {

    private final PraiseRepository repository;

    @Autowired
    public PraiseController(PraiseRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/{id}")
    public @ResponseBody PraiseDto getPraiseById(@PathVariable("id") long id) {
        Praise praise = repository.findById(id);
        return new PraiseDto(praise);
    }
}
