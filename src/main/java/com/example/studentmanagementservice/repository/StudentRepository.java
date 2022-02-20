package com.example.studentmanagementservice.repository;

import com.example.studentmanagementservice.entity.Student;
import com.example.studentmanagementservice.enumaration.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findByIdAndStatus(Long id, Status status);
    @Query("SELECT s FROM Student s WHERE s.status='INACTIVE'")
    List<Student> findAllByStatusInactive();
}
