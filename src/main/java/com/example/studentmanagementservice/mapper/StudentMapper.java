package com.example.studentmanagementservice.mapper;

import com.example.studentmanagementservice.dto.StudentRequest;
import com.example.studentmanagementservice.dto.StudentResponse;
import com.example.studentmanagementservice.entity.InactiveStudent;
import com.example.studentmanagementservice.entity.Student;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface StudentMapper {
    StudentMapper INSTANCE = Mappers.getMapper(StudentMapper.class);

    StudentResponse studentToResponse(Student student);
    Student requestToStudent(StudentRequest studentRequest);
    InactiveStudent studentToInactiveStudent(Student student);

}
