package com.yasho.bugtrackingservice.jpa;

import com.yasho.bugtrackingservice.domain.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProjectRepo extends JpaRepository<Project, Long> {
    Optional<Project> findByProjectId(String projectId);

}
