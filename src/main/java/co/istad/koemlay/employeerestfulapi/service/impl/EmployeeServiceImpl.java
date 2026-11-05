package co.istad.koemlay.employeerestfulapi.service.impl;

import co.istad.koemlay.employeerestfulapi.Mapper.EmployeeMapper;
import co.istad.koemlay.employeerestfulapi.domain.Employee;
import co.istad.koemlay.employeerestfulapi.dto.CreateEmployeeRequest;
import co.istad.koemlay.employeerestfulapi.dto.EmployeeResponse;
import co.istad.koemlay.employeerestfulapi.dto.UpdateEmployeeRequest;
import co.istad.koemlay.employeerestfulapi.repository.EmployeeRepository;
import co.istad.koemlay.employeerestfulapi.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository repository;
    private final EmployeeMapper mapper;

    private static int nextId = 3;

    @Override
    public List<EmployeeResponse> getAll() {
        return repository.getEmployees()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    public EmployeeResponse getEmployeeById(Integer id) {
        return repository.getEmployees()
                .stream()
                .filter(emp -> emp.getId().equals(id))
                .findFirst()
                .map(mapper::toResponse)
                .orElseThrow(
                        () -> new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Employee whit id: %d not found".formatted(id)
                        )
                );
    }

    @Override
    public EmployeeResponse createEmployee(CreateEmployeeRequest employeeRequest) {

        if (checkIfEmailExist(employeeRequest.email())){
            throw  new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Email already exists"
            );
        }

        Employee employee = mapper.fromCreateEmployeeRequest(employeeRequest);
        employee.setId(nextId++);
        employee.setIsActive(Boolean.TRUE);


        repository.getEmployees().add(employee);
        return mapper.toResponse(employee);
    }

    @Override
    public EmployeeResponse updateEmployeeById(Integer id, UpdateEmployeeRequest updateEmployeeRequest) {
        Employee employee = repository.getEmployees()
                .stream()
                .filter(emp -> emp.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Employee id not found %d".formatted(id)
                ));

        boolean emailExist = repository.getEmployees()
                .stream()
                .anyMatch(emp ->
                        !emp.getId().equals(id)
                                && emp.getEmail().equalsIgnoreCase(
                                updateEmployeeRequest.email())
                );
        if (emailExist) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Email already exists"
            );
        }
        employee.setName(updateEmployeeRequest.name());
        employee.setEmail(updateEmployeeRequest.email());
        employee.setSalary(updateEmployeeRequest.salary());
        employee.setHireDate(updateEmployeeRequest.hireDate());
        employee.setIsActive(updateEmployeeRequest.isActive());

        return mapper.toResponse(employee);
    }

    @Override
    public void deleteEmployeeById(Integer id) {
        boolean remove = repository.getEmployees()
                .removeIf(emp -> emp.getId().equals(id));

        if (!remove) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Employee with id: %d not found".formatted(id)
            );
        }
    }

    @Override
    public List<EmployeeResponse> searchEmployees(String name) {
        return repository.getEmployees()
                .stream()
                .filter(emp -> emp.getName()
                        .toLowerCase(Locale.ROOT)
                        .contains(name.trim().toLowerCase(Locale.ROOT)))
                .map(mapper::toResponse)
                .toList();
    }

    private boolean checkIfEmailExist(String email) {
        return repository.getEmployees()
                .stream()
                .anyMatch(emp -> emp.getEmail().equals(email));
    }

}