package com.yasho.bugtrackingservice.jpa;

import com.yasho.bugtrackingservice.domain.Issue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IssueRepo extends JpaRepository<Issue, Long> {
    List<Issue> findByProjectId(String projectId);
    Optional<Issue> findByProjectIdAndIssueId(long projectId, String issueId);
}
