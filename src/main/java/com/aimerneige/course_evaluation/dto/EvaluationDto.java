package com.aimerneige.course_evaluation.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.aimerneige.course_evaluation.model.Evaluation;

public class EvaluationDto {

    private Long id;
    private Long praiseId;
    private List<Long> questionIds;
    private Long studentId;
    private Long courseId;

    public EvaluationDto() {
    }

    public EvaluationDto(Evaluation evaluation) {
        this.id = evaluation.getId();
        this.praiseId = evaluation.getPraise().getId();
        this.questionIds = evaluation.getQuestions()
                .stream().map(question -> question.getId())
                .collect(Collectors.toList());
        this.studentId = evaluation.getStudent().getId();
        this.courseId = evaluation.getCourse().getId();
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPraiseId() {
        return this.praiseId;
    }

    public void setPraiseId(Long praiseId) {
        this.praiseId = praiseId;
    }

    public List<Long> getQuestionIds() {
        return this.questionIds;
    }

    public void setQuestionIds(List<Long> questionIds) {
        this.questionIds = questionIds;
    }

    public Long getStudentId() {
        return this.studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getCourseId() {
        return this.courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }
}
