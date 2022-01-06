package com.aimerneige.course_evaluation.controller;

import java.util.ArrayList;
import java.util.List;

import com.aimerneige.course_evaluation.dto.QuestionDto;
import com.aimerneige.course_evaluation.entity.Evaluation;
import com.aimerneige.course_evaluation.entity.Question;
import com.aimerneige.course_evaluation.param.QuestionParam;
import com.aimerneige.course_evaluation.repository.EvaluationRepository;
import com.aimerneige.course_evaluation.repository.QuestionRepository;
import com.aimerneige.course_evaluation.response.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/question")
public class QuestionController {

    private final QuestionRepository repository;
    private final EvaluationRepository evaluationRepository;

    private final Response questionNotFoundResponse = Response.notFound("Question not found");
    private final Response evaluationNoFoundResponse = Response.notFound("Evaluation not found");

    @Autowired
    public QuestionController(QuestionRepository repository, EvaluationRepository evaluationRepository) {
        this.repository = repository;
        this.evaluationRepository = evaluationRepository;
    }

    @GetMapping("")
    public Response getAllQuestion() {
        Iterable<Question> questions = repository.findAll();
        List<QuestionDto> dtos = new ArrayList<>();
        for (Question question : questions) {
            dtos.add(new QuestionDto(question));
        }
        if (dtos.isEmpty()) {
            return questionNotFoundResponse;
        }
        return Response.success(dtos);
    }

    @PostMapping("")
    public Response createQuestion(@RequestBody QuestionParam param) {
        // check if evaluation exists
        long evaluationId = param.getEvaluationId();
        Evaluation evaluation = evaluationRepository.findById(evaluationId);
        if (evaluation == null) {
            return evaluationNoFoundResponse;
        }
        // save question
        Question question = new Question();
        question.setContent(param.getContent());
        question.setScore(param.getScore());
        question.setEvaluation(evaluation);
        repository.save(question);
        return Response.success(new QuestionDto(question));
    }

    @GetMapping("/{id}")
    public Response getQuestionById(@PathVariable("id") long id) {
        Question question = repository.findById(id);
        if (question == null) {
            return questionNotFoundResponse;
        }
        return Response.success(new QuestionDto(question));
    }

    @PutMapping("/{id}")
    public Response updateQuestion(@PathVariable("id") long id, @RequestBody QuestionParam param) {
        // check if question exists
        Question question = repository.findById(id);
        if (question == null) {
            return questionNotFoundResponse;
        }
        // check if evaluation exists
        long evaluationId = param.getEvaluationId();
        Evaluation evaluation = evaluationRepository.findById(evaluationId);
        if (evaluation == null) {
            return evaluationNoFoundResponse;
        }
        // update question
        question.setContent(param.getContent());
        question.setScore(param.getScore());
        question.setEvaluation(evaluation);
        repository.save(question);
        return Response.success(new QuestionDto(question));
    }

    @DeleteMapping("/{id}")
    public Response deleteQuestion(@PathVariable("id") long id) {
        Question question = repository.findById(id);
        if (question == null) {
            return questionNotFoundResponse;
        }
        repository.delete(question);
        return Response.success();
    }

    @GetMapping("/evaluation")
    public Response getAllQuestionByEvaluationId(@RequestParam long evaluationId) {
        Iterable<Question> questions = repository.findByEvaluationId(evaluationId);
        List<QuestionDto> dtos = new ArrayList<>();
        for (Question question : questions) {
            dtos.add(new QuestionDto(question));
        }
        if (dtos.isEmpty()) {
            return questionNotFoundResponse;
        }
        return Response.success(dtos);
    }
}
