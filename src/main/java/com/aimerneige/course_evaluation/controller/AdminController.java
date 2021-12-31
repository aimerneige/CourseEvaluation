package com.aimerneige.course_evaluation.controller;

import com.aimerneige.course_evaluation.repository.AdminRepository;
import com.aimerneige.course_evaluation.dto.AdminDto;
import com.aimerneige.course_evaluation.model.Admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final AdminRepository repository;

    @Autowired
    public AdminController(AdminRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/{id}")
    public @ResponseBody AdminDto getAdminById(@PathVariable("id") long id) {
        Admin admin = repository.findById(id);
        return new AdminDto(admin);
    }
}
