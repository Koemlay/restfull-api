package co.istad.koemlay.employeerestfulapi.Mapper;

import co.istad.koemlay.employeerestfulapi.domain.Employee;
import co.istad.koemlay.employeerestfulapi.dto.CreateEmployeeRequest;
import co.istad.koemlay.employeerestfulapi.dto.EmployeeResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    // Map from Domain to DTO
    EmployeeResponse toResponse(Employee employee);

    // Map from DTO to domain
    Employee fromCreateEmployeeRequest(CreateEmployeeRequest employeeRequest);
}