package com.example.studentmanagementservice.service.impl;

import com.example.studentmanagementservice.dto.StudentResponse;
import com.example.studentmanagementservice.entity.Student;
import com.example.studentmanagementservice.repository.StudentRepository;
import com.example.studentmanagementservice.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;

    @Override
    public List<StudentResponse> findAll() {
        final List<Student> students = studentRepository.findAll();
        List<StudentResponse> response = students.stream().map(student -> {
            StudentResponse obj = new StudentResponse();
            obj.setName(student.getName());
            obj.setId(student.getId());
            obj.setSurname(student.getSurname());
            obj.setEmail(student.getEmail());
            obj.setPhoneNumber(student.getPhoneNumber());
            obj.setStatus(student.getStatus());
            obj.setCreatedOn(student.getCreatedOn());
            obj.setUpdatedOn(student.getUpdatedOn());
            return obj;
        }).toList();

//        for (Student student : students){
//            StudentResponse obj = new StudentResponse();
//            obj.setName(student.getName());
//            obj.setId(student.getId());
//            obj.setSurname(student.getSurname());
//            obj.setEmail(student.getEmail());
//            obj.setPhoneNumber(student.getPhoneNumber());
//            obj.setStatus(student.getStatus());
//            obj.setCreatedOn(student.getCreatedOn());
//            obj.setUpdatedOn(student.getUpdatedOn());
//
//            response.add(obj);
//        }
        return response;

    }
}
