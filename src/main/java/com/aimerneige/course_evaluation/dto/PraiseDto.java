package com.aimerneige.course_evaluation.dto;

import com.aimerneige.course_evaluation.entity.Praise;

public class PraiseDto {

    private Long id;
    private String content;
    private Long evaluationId;

    public PraiseDto() {
    }

    public PraiseDto(Praise praise) {
        this.id = praise.getId();
        this.content = praise.getContent();
        if (praise.getEvaluation() != null) {
            this.evaluationId = praise.getEvaluation().getId();
        } else {
            this.evaluationId = 0L;
        }
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

    public Long getEvaluationId() {
        return this.evaluationId;
    }

    public void setEvaluationId(Long evaluationId) {
        this.evaluationId = evaluationId;
    }
}
