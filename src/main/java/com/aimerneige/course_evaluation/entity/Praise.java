package com.aimerneige.course_evaluation.entity;

import javax.persistence.*;

@Entity
public class Praise {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id; // 主键

    @Column(length = 256)
    private String content; // 评语

    @OneToOne
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

    public Evaluation getEvaluation() {
        return this.evaluation;
    }

    public void setEvaluation(Evaluation evaluation) {
        this.evaluation = evaluation;
    }
}
