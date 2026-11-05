package co.istad.koemlay.employeerestfulapi.controller;

import co.istad.koemlay.employeerestfulapi.dto.CreateEmployeeRequest;
import co.istad.koemlay.employeerestfulapi.dto.EmployeeResponse;
import co.istad.koemlay.employeerestfulapi.dto.UpdateEmployeeRequest;
import co.istad.koemlay.employeerestfulapi.service.EmployeeService;
import io.swagger.v3.oas.models.security.SecurityScheme;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService service;

    @GetMapping
    public List<EmployeeResponse> getAllEmployees() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public EmployeeResponse getEmployeeById(@PathVariable Integer id) {
        return service.getEmployeeById(id);
    }

    @PostMapping
    public EmployeeResponse createEmployee(
            @Valid @RequestBody CreateEmployeeRequest createRequest
    ) {
        return service.createEmployee(createRequest);
    }

    @GetMapping("/search")
    public List<EmployeeResponse> searchEmployee(
            @RequestParam String name
    ){
        return service.searchEmployees(name);
    }

    @PutMapping("/{id}")
    public EmployeeResponse updateEmployeeById(
            @PathVariable Integer id,
            @Valid @RequestBody UpdateEmployeeRequest request
    ) {
        return service.updateEmployeeById(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEmployeeById(
            @PathVariable Integer id
    ){
        service.deleteEmployeeById(id);
    }

}