package com.aimerneige.course_evaluation.dto;

import com.aimerneige.course_evaluation.model.Admin;

public class AdminDto {

    private Long id;
    private String name;
    private String username;
    private String password;

    public AdminDto() {
    }

    public AdminDto(Admin admin) {
        this.id = admin.getId();
        this.name = admin.getName();
        this.username = admin.getUsername();
        this.password = admin.getPassword();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
