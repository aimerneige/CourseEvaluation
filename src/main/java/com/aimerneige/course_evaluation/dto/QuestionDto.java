package com.aimerneige.course_evaluation.dto;

import com.aimerneige.course_evaluation.entity.Question;

public class QuestionDto {

    private Long id;
    private String content;
    private Integer score;
    private Long evaluationId;

    public QuestionDto() {
    }

    public QuestionDto(Question question) {
        this.id = question.getId();
        this.content = question.getContent();
        this.score = question.getScore();
        this.evaluationId = question.getEvaluation().getId();
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getScore() {
        return this.score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Long getEvaluationId() {
        return evaluationId;
    }

    public void setEvaluationId(Long evaluationId) {
        this.evaluationId = evaluationId;
    }
}
