package co.istad.koemlay.employeerestfulapi.dto;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDate;

public record UpdateEmployeeRequest(

        @NotBlank(message = "Name is required")
        @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
        String name,

        @NotBlank(message = "Email is required")
        @Email(message = "Invalid email format")
        String email,

        @NotNull(message = "Salary is required")
        @Positive(message = "Salary must be greater then zero")
        BigDecimal salary,

        @NotNull(message = "Hire date required")
        LocalDate hireDate,

        @NotNull(message = "Active status is required")
        Boolean isActive
) {
}
