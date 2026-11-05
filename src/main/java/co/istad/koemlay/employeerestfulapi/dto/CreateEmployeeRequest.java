package co.istad.koemlay.employeerestfulapi.dto;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDate;

public record CreateEmployeeRequest(

        @NotBlank(message = "Name is required")
        @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
        String name,

        @Email(message = "Invalid email format")
        String email,

        @Positive(message = "Salary must be greater than zero")
        @NotNull(message = "Salary is required")
        BigDecimal salary,


        @NotBlank(message = "Hired date is required\n Format: yyy-MM-dd")
        String  hireDate
) {
}
