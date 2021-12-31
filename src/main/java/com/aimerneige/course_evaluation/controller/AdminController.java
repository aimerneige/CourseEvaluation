package com.aimerneige.course_evaluation.controller;

import com.aimerneige.course_evaluation.repository.AdminRepository;
import com.aimerneige.course_evaluation.response.Response;
import com.aimerneige.course_evaluation.utils.HashUtils;

import java.util.ArrayList;
import java.util.List;

import com.aimerneige.course_evaluation.dto.AdminDto;
import com.aimerneige.course_evaluation.model.Admin;
import com.aimerneige.course_evaluation.param.AdminParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final AdminRepository repository;

    @Autowired
    public AdminController(AdminRepository repository) {
        this.repository = repository;
    }

    @GetMapping("")
    public Response getAllAdmins() {
        Iterable<Admin> admins = repository.findAll();
        List<AdminDto> dtos = new ArrayList<>();
        for (Admin admin : admins) {
            dtos.add(new AdminDto(admin));
        }
        return Response.success(dtos);
    }

    @PostMapping("")
    public Response createAdmin(@RequestBody AdminParam param) {
        // check if username exists
        if (repository.findByUsername(param.getUsername()) != null) {
            return Response.badRequest("Username already exists");
        }
        // save admin
        Admin admin = new Admin();
        admin.setName(param.getName());
        admin.setUsername(param.getUsername());
        admin.setPassword(HashUtils.md5(param.getPassword()));
        repository.save(admin);
        return Response.success(new AdminDto(admin));
    }

    @GetMapping("/{id}")
    public Response getAdminById(@PathVariable("id") long id) {
        Admin admin = repository.findById(id);
        if (admin == null) {
            return Response.notFound();
        }
        return Response.success(new AdminDto(admin));
    }

    @PutMapping("/{id}")
    public Response updateAdminById(@PathVariable("id") long id, @RequestBody AdminParam param) {
        Admin admin = repository.findById(id);
        if (admin == null) {
            return Response.notFound();
        }
        // check if username exists
        if (repository.findByUsername(param.getUsername()) != null
                && !admin.getUsername().equals(param.getUsername())) {
            return Response.badRequest("Username already exists");
        }
        // update admin
        admin.setName(param.getName());
        admin.setUsername(param.getUsername());
        admin.setPassword(HashUtils.md5(param.getPassword()));
        repository.save(admin);
        return Response.success(new AdminDto(admin));
    }

    @DeleteMapping("/{id}")
    public Response deleteAdminById(@PathVariable("id") long id) {
        Admin admin = repository.findById(id);
        if (admin == null) {
            return Response.notFound();
        }
        repository.delete(admin);
        return Response.success();
    }

    @GetMapping("/search")
    public Response searchAdminsByName(@RequestParam String name) {
        Iterable<Admin> admins = repository.findByName(name);
        List<AdminDto> dtos = new ArrayList<>();
        for (Admin admin : admins) {
            dtos.add(new AdminDto(admin));
        }
        return Response.success(dtos);
    }
}
