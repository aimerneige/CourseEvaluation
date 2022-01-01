package com.aimerneige.course_evaluation.controller;

import com.aimerneige.course_evaluation.dto.QuestionDto;
import com.aimerneige.course_evaluation.entity.Question;
import com.aimerneige.course_evaluation.repository.QuestionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/question")
public class QuestionController {

    private final QuestionRepository repository;

    @Autowired
    public QuestionController(QuestionRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/{id}")
    public @ResponseBody QuestionDto getQuestionById(@PathVariable("id") long id) {
        Question question = repository.findById(id);
        return new QuestionDto(question);
    }
}
