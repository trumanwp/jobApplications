package com.truman.jobs.domain.dto;

import com.truman.jobs.domain.entities.JobPriority;
import com.truman.jobs.domain.entities.JobStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public record JobDto(
        UUID id,
        String title,
        String description,
        LocalDateTime dueDate,
        JobPriority priority,
        JobStatus status
) {
}
