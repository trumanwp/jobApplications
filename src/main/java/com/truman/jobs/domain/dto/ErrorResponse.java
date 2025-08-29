package com.truman.jobs.domain.dto;

public record ErrorResponse(
        int status,
        String message,
        String details
) {
}
