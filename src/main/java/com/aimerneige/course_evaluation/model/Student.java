package com.aimerneige.course_evaluation.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Student {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id; // 主键

    @Column(length = 10, unique = true)
    private String idNumber; // 学号

    @Column(length = 10)
    private String name; // 姓名

    @Column(length = 11)
    private String phone; // 电话

    @Column(length = 2)
    private String sex; // 性别

    @Column
    private String email; // 邮箱

    @Column
    private String password; // 密码

    @Column
    private Integer age; // 年龄

    @OneToMany(mappedBy = "student")
    public Set<Evaluation> evaluations; // 评价

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdNumber() {
        return this.idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSex() {
        return this.sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getAge() {
        return this.age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Set<Evaluation> getEvaluations() {
        return this.evaluations;
    }

    public void setEvaluations(Set<Evaluation> evaluations) {
        this.evaluations = evaluations;
    }
}
