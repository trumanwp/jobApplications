package com.truman.jobs.repositories;

import com.truman.jobs.domain.entities.JobList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface JobListRepository extends JpaRepository<JobList, UUID> {
}
