package com.example.studentmanagementservice.controller;

import com.example.studentmanagementservice.dto.StudentResponse;
import com.example.studentmanagementservice.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;

    @GetMapping
    public List<StudentResponse> findAll(){
        return studentService.findAll();
    }
}

