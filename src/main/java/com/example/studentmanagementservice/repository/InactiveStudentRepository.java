package com.example.studentmanagementservice.repository;

import com.example.studentmanagementservice.entity.InactiveStudent;
import org.springframework.data.repository.CrudRepository;

public interface InactiveStudentRepository extends CrudRepository<InactiveStudent,Long> {
}
