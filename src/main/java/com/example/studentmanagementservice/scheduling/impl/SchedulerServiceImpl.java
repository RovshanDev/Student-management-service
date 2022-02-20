package com.example.studentmanagementservice.scheduling.impl;

import com.example.studentmanagementservice.entity.InactiveStudent;
import com.example.studentmanagementservice.entity.Student;
import com.example.studentmanagementservice.mapper.StudentMapper;
import com.example.studentmanagementservice.repository.InactiveStudentRepository;
import com.example.studentmanagementservice.repository.StudentRepository;
import com.example.studentmanagementservice.scheduling.SchedulerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class SchedulerServiceImpl implements SchedulerService {
    private final StudentRepository studentRepository;
    private final InactiveStudentRepository inactiveStudentRepository;

    @Override
    @Scheduled(fixedRateString = "${fixed.rate}")
    @Transactional(rollbackFor = Exception.class)
    public void deleteInactiveVisitor() {
        log.info("STARTED");
        final List<Student> allByStatusInactive = studentRepository.findAllByStatusInactive();
        if (!allByStatusInactive.isEmpty()) {
            log.info(allByStatusInactive.get(0).getName());
            final List<InactiveStudent> inactiveStudents = allByStatusInactive.stream()
                    .map(StudentMapper.INSTANCE::studentToInactiveStudent)
                    .toList();
            inactiveStudentRepository.saveAll(inactiveStudents);

            allByStatusInactive.forEach(s -> {
                studentRepository.deleteById(s.getId());
            });
        }

        log.info("COMPLETED");
    }
}
