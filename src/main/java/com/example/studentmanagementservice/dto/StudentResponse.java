package com.example.studentmanagementservice.dto;

import com.example.studentmanagementservice.enumaration.Status;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StudentResponse {
    Long id;
    String name;
    String surname;
    String email;
    String phoneNumber;
    Status status;
    LocalDateTime createdOn;
    LocalDateTime updatedOn;
}
