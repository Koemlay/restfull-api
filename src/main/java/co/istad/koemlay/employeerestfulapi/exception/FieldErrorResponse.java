package co.istad.koemlay.employeerestfulapi.exception;

import lombok.Builder;

@Builder
public record FieldErrorResponse(

        String field,

        String message
) {
}
