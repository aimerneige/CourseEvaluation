package com.aimerneige.course_evaluation.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Course {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id; // 主键

    @Column(length = 10)
    private String title; // 课程名称

    @Column(length = 256)
    private String description; // 课程描述

    @ManyToOne
    @JoinColumn(nullable = false)
    private Teacher teacher; // 教师

    @ManyToMany
    public Set<Student> students; // 学生

    @OneToMany(mappedBy = "course")
    public Set<Evaluation> evaluations; // 评价

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Teacher getTeacher() {
        return this.teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Set<Student> getStudents() {
        return this.students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    public Set<Evaluation> getEvaluations() {
        return this.evaluations;
    }

    public void setEvaluations(Set<Evaluation> evaluations) {
        this.evaluations = evaluations;
    }
}
