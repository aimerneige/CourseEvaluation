package com.aimerneige.course_evaluation.controller;

import java.util.ArrayList;
import java.util.List;

import com.aimerneige.course_evaluation.dto.PraiseDto;
import com.aimerneige.course_evaluation.entity.Evaluation;
import com.aimerneige.course_evaluation.entity.Praise;
import com.aimerneige.course_evaluation.param.PraiseParam;
import com.aimerneige.course_evaluation.repository.EvaluationRepository;
import com.aimerneige.course_evaluation.repository.PraiseRepository;
import com.aimerneige.course_evaluation.response.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.ParameterResolutionDelegate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/praise")
public class PraiseController {

    private final PraiseRepository repository;
    private final EvaluationRepository evaluationRepository;

    private final Response praiseNotFoundResponse = Response.notFound("Praise not found");
    private final Response evaluationNotFoundResponse = Response.notFound("Evaluation not found");
    private final Response evaluationHasBeenPraised = Response.badRequest("Evaluation has already been praised");

    @Autowired
    public PraiseController(PraiseRepository repository, EvaluationRepository evaluationRepository) {
        this.repository = repository;
        this.evaluationRepository = evaluationRepository;
    }

    @GetMapping("")
    public Response getAllPraise() {
        Iterable<Praise> praises = repository.findAll();
        List<PraiseDto> dtos = new ArrayList<>();
        for (Praise praise : praises) {
            dtos.add(new PraiseDto(praise));
        }
        if (dtos.isEmpty()) {
            return praiseNotFoundResponse;
        }
        return Response.success(dtos);
    }

    @PostMapping("")
    public Response createPraise(@RequestBody PraiseParam param) {
        // check if evaluation exists
        long evaluationId = param.getEvaluationId();
        Evaluation evaluation = evaluationRepository.findById(evaluationId);
        if (evaluation == null) {
            return evaluationNotFoundResponse;
        }
        // check if evaluation has already been praised
        Praise check = repository.findByEvaluationId(evaluationId);
        if (check != null) {
            return evaluationHasBeenPraised;
        }
        // save praise
        Praise praise = new Praise();
        praise.setContent(param.getContent());
        praise.setEvaluation(evaluation);
        repository.save(praise);
        return Response.success(new PraiseDto(praise));
    }

    @GetMapping("/{id}")
    public Response getPraiseById(@PathVariable("id") long id) {
        Praise praise = repository.findById(id);
        if (praise == null) {
            return praiseNotFoundResponse;
        }
        return Response.success(new PraiseDto(praise));
    }

    @PutMapping("/{id}")
    public Response updatePraise(@PathVariable("id") long id, @RequestBody PraiseParam param) {
        // check if praise exists
        Praise praise = repository.findById(id);
        if (praise == null) {
            return praiseNotFoundResponse;
        }
        // check if evaluation exists
        long evaluationId = param.getEvaluationId();
        Evaluation evaluation = evaluationRepository.findById(evaluationId);
        if (evaluation == null) {
            return evaluationNotFoundResponse;
        }
        // check if evaluation has already been praised
        Praise check = repository.findByEvaluationId(evaluationId);
        if (check != null) {
            return evaluationHasBeenPraised;
        }
        // update praise
        praise.setContent(param.getContent());
        praise.setEvaluation(evaluation);
        repository.save(praise);
        return Response.success(new PraiseDto(praise));
    }

    @DeleteMapping("/{id}")
    public Response deletePraise(@PathVariable("id") long id) {
        Praise praise = repository.findById(id);
        if (praise == null) {
            return praiseNotFoundResponse;
        }
        repository.delete(praise);
        return Response.success();
    }

    @GetMapping("/evaluation")
    public Response getPraiseByEvaluationId(@RequestParam long evaluationId) {
        Praise praise = repository.findByEvaluationId(evaluationId);
        if (praise == null) {
            return praiseNotFoundResponse;
        }
        return Response.success(new PraiseDto(praise));
    }
}
