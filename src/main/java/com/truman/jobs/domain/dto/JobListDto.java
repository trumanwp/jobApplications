package com.truman.jobs.domain.dto;

import java.util.List;
import java.util.UUID;

public record JobListDto(
        UUID id,
        String title,
        String description,
        Integer count,
        Double progress,
        List<JobDto> jobs
) {
}
