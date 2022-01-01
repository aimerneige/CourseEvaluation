package com.aimerneige.course_evaluation.entity;

import javax.persistence.*;

@Entity
public class Question {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id; // 主键

    @Column(length = 256)
    private String content; // 问题

    @Column
    private Integer score; // 分数

    @ManyToOne
    @JoinColumn(nullable = false)
    public Evaluation evaluation; // 评价

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

    public Evaluation getEvaluation() {
        return this.evaluation;
    }

    public void setEvaluation(Evaluation evaluation) {
        this.evaluation = evaluation;
    }
}
