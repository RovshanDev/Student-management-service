package com.example.studentmanagementservice.service.impl;

import com.example.studentmanagementservice.dto.StudentRequest;
import com.example.studentmanagementservice.dto.StudentResponse;
import com.example.studentmanagementservice.entity.Student;
import com.example.studentmanagementservice.enumaration.Status;
import com.example.studentmanagementservice.exception.DataNotFoundException;
import com.example.studentmanagementservice.exception.response.ResponseMessage;
import com.example.studentmanagementservice.repository.StudentRepository;
import com.example.studentmanagementservice.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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

    @Override
    public StudentResponse create(StudentRequest request) {
        Student student = new Student();
        student.setName(request.getName());
        student.setSurname(request.getSurname());
        student.setEmail(request.getEmail());
        student.setPhoneNumber(request.getPhoneNumber());
        student.setCreatedOn(LocalDateTime.now());
        student.setUpdatedOn(LocalDateTime.now());
        student.setStatus(Status.ACTIVE);
        final Student savedStudent = studentRepository.save(student);
        StudentResponse response = new StudentResponse();
        response.setId(savedStudent.getId());
        response.setName(savedStudent.getName());
        response.setSurname(savedStudent.getSurname());
        response.setEmail(savedStudent.getEmail());
        response.setPhoneNumber(savedStudent.getPhoneNumber());
        response.setStatus(savedStudent.getStatus());
        response.setCreatedOn(savedStudent.getCreatedOn());
        response.setUpdatedOn(savedStudent.getUpdatedOn());
        return response;
    }

    @Override
    public StudentResponse update(Long studentId, StudentRequest studentRequest) {
        final Optional<Student> byIdAndStatus = studentRepository.findByIdAndStatus(studentId, Status.ACTIVE);
        byIdAndStatus.orElseThrow(() -> new DataNotFoundException(ResponseMessage.DATA_NOT_FOUND));
        Student student = byIdAndStatus.get();
        student.setName(studentRequest.getName());
        student.setSurname(studentRequest.getSurname());
        student.setEmail(studentRequest.getEmail());
        student.setPhoneNumber(studentRequest.getPhoneNumber());
        student.setUpdatedOn(LocalDateTime.now());
        final Student savedStudent = studentRepository.save(student);
        StudentResponse response = new StudentResponse();
        response.setId(savedStudent.getId());
        response.setName(savedStudent.getName());
        response.setSurname(savedStudent.getSurname());
        response.setEmail(savedStudent.getEmail());
        response.setPhoneNumber(savedStudent.getPhoneNumber());
        response.setStatus(savedStudent.getStatus());
        response.setCreatedOn(savedStudent.getCreatedOn());
        response.setUpdatedOn(savedStudent.getUpdatedOn());
        return response;
    }

    @Override
    public void delete(Long studentId) {
        final Optional<Student> st = studentRepository.findByIdAndStatus(studentId, Status.ACTIVE);
        final Student student = st.get();
        student.setStatus(Status.INACTIVE);
        studentRepository.save(student);
    }


}
