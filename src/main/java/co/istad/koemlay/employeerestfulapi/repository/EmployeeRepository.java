package co.istad.koemlay.employeerestfulapi.repository;

import co.istad.koemlay.employeerestfulapi.domain.Employee;
import lombok.Getter;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
@Getter
public class EmployeeRepository {
    private final List<Employee> employees;

    public EmployeeRepository() {
        employees = new ArrayList<>();

        Employee emp1 = new Employee();
        emp1.setId(1);
        emp1.setName("Tony Stark");
        emp1.setEmail("tony@gmail.com");
        emp1.setSalary(BigDecimal.valueOf(500));
        emp1.setHireDate(
                LocalDate.of(2025, 10, 30)
        );
        emp1.setIsActive(true);
        employees.add(emp1);

        Employee emp2 = new Employee();
        emp2.setId(2);
        emp2.setName("Yan Pech");
        emp2.setEmail("yanpech@gmail.com");
        emp2.setSalary(BigDecimal.valueOf(600));
        emp2.setHireDate(
                LocalDate.of(2026, 10, 30)
        );
        emp2.setIsActive(true);
        employees.add(emp2);
    }
}
