package co.istad.koemlay.employeerestfulapi.service;

import co.istad.koemlay.employeerestfulapi.dto.CreateEmployeeRequest;
import co.istad.koemlay.employeerestfulapi.dto.EmployeeResponse;
import co.istad.koemlay.employeerestfulapi.dto.UpdateEmployeeRequest;

import java.util.List;

public interface EmployeeService {

    //CRUD, Create, Read, Update, Delete
    List<EmployeeResponse> getAll();
    EmployeeResponse getEmployeeById(Integer id);

    EmployeeResponse createEmployee(CreateEmployeeRequest employeeRequest);

    List<EmployeeResponse> searchEmployees(String name);

    EmployeeResponse updateEmployeeById(Integer id, UpdateEmployeeRequest updateEmployeeRequest);

    void deleteEmployeeById(Integer id);

}
