package com.example.studentmanagementservice.service.impl;

import com.example.studentmanagementservice.dto.StudentRequest;
import com.example.studentmanagementservice.dto.StudentResponse;
import com.example.studentmanagementservice.entity.Student;
import com.example.studentmanagementservice.enumaration.Status;
import com.example.studentmanagementservice.exception.DataNotFoundException;
import com.example.studentmanagementservice.exception.response.ResponseMessage;
import com.example.studentmanagementservice.mapper.StudentMapper;
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
        return students.stream().map(StudentMapper.INSTANCE::studentToResponse).toList();
    }

    @Override
    public StudentResponse create(StudentRequest request) {
        final Student student = StudentMapper.INSTANCE.requestToStudent(request);
        student.setCreatedOn(LocalDateTime.now());
        student.setUpdatedOn(LocalDateTime.now());
        student.setStatus(Status.ACTIVE);

        return StudentMapper.INSTANCE.studentToResponse(studentRepository.save(student));
    }

    @Override
    public StudentResponse update(Long studentId, StudentRequest studentRequest) {
        final Optional<Student> byIdAndStatus = studentRepository.findByIdAndStatus(studentId, Status.ACTIVE);
        byIdAndStatus.orElseThrow(() -> new DataNotFoundException(ResponseMessage.DATA_NOT_FOUND));

        final Student student = StudentMapper.INSTANCE.requestToStudent(studentRequest);
        student.setUpdatedOn(LocalDateTime.now());

        return StudentMapper.INSTANCE.studentToResponse(studentRepository.save(student));
    }

    @Override
    public void delete(Long studentId) {
        final Optional<Student> st = studentRepository.findByIdAndStatus(studentId, Status.ACTIVE);
        final Student student = st.get();
        student.setStatus(Status.INACTIVE);
        studentRepository.save(student);
    }


}
