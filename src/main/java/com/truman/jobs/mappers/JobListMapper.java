package com.truman.jobs.mappers;

import com.truman.jobs.domain.dto.JobListDto;
import com.truman.jobs.domain.entities.JobList;

public interface JobListMapper {

    JobList fromDto(JobListDto jobListDto);

    JobListDto toDto(JobList jobList);
}
