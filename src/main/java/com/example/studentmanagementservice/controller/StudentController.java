package com.example.studentmanagementservice.controller;

import com.example.studentmanagementservice.dto.StudentRequest;
import com.example.studentmanagementservice.dto.StudentResponse;
import com.example.studentmanagementservice.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;

    @GetMapping
    public List<StudentResponse> findAll() {

        return studentService.findAll();
    }

    @PostMapping
    public StudentResponse create(@Valid @RequestBody StudentRequest studentRequest) {
        return studentService.create(studentRequest);
    }
}

