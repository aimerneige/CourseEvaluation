package com.aimerneige.course_evaluation.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.aimerneige.course_evaluation.entity.Evaluation;
import com.aimerneige.course_evaluation.entity.Question;

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
        if (evaluation.getPraise() != null) {
            this.praiseId = evaluation.getPraise().getId();
        }
        this.questionIds = new ArrayList<>();
        Set<Question> questions = evaluation.getQuestions();
        if (questions != null) {
            for (Question question : questions) {
                this.questionIds.add(question.getId());
            }
        }
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
