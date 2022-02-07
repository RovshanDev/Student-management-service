package com.example.studentmanagementservice.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StudentRequest {
    @NotNull
    @Size(min = 3, max = 15)
    String name;
    @Size(min = 3, max = 15)
    String surname;
    @Email(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$",message = "Email not valid")
    String email;
    @Size(min = 10, max = 15)
    @Pattern(regexp = "[0-9]+")
    String phoneNumber;
}
