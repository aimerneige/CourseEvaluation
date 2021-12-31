package com.aimerneige.course_evaluation.dto;

import java.util.List;
import java.util.Set;

import com.aimerneige.course_evaluation.model.Course;
import com.aimerneige.course_evaluation.model.Teacher;

public class TeacherDto {

    private Long id;
    private String idNumber;
    private String name;
    private String phone;
    private String sex;
    private Integer age;
    private List<Long> courseIds;

    public TeacherDto() {
    }

    public TeacherDto(Teacher teacher) {
        this.id = teacher.getId();
        this.idNumber = teacher.getIdNumber();
        this.name = teacher.getName();
        this.phone = teacher.getPhone();
        this.sex = teacher.getPhone();
        this.age = teacher.getAge();
        Set<Course> courses = teacher.getCourses();
        if (courses != null) {
            for (Course course : courses) {
                this.courseIds.add(course.getId());
            }
        }
    }

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

    public Integer getAge() {
        return this.age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public List<Long> getCourseIds() {
        return this.courseIds;
    }

    public void setCourseIds(List<Long> courseIds) {
        this.courseIds = courseIds;
    }
}
