package com.aimerneige.course_evaluation.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/")
public class IndexController {

    @ApiOperation(value = "Index Test", notes = "Index Test")
    @GetMapping("")
    public String index() {
        return "Hello World!";
    }
}
