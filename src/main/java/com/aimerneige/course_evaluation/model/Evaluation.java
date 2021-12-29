package com.aimerneige.course_evaluation.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Evaluation {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id; // 主键

    @OneToOne(mappedBy = "evaluation")
    private Praise praise; // 评语

    @OneToMany(mappedBy = "evaluation")
    public Set<Question> questions; // 问题

    @ManyToOne
    @JoinColumn(nullable = false)
    private Student student; // 学生

    @ManyToOne
    @JoinColumn(nullable = false)
    private Course course; // 课程

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Praise getPraise() {
        return this.praise;
    }

    public void setPraise(Praise praise) {
        this.praise = praise;
    }

    public Set<Question> getQuestions() {
        return this.questions;
    }

    public void setQuestions(Set<Question> questions) {
        this.questions = questions;
    }

    public Student getStudent() {
        return this.student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return this.course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
