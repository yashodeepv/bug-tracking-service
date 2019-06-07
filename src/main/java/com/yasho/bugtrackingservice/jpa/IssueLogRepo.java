package com.yasho.bugtrackingservice.jpa;

import com.yasho.bugtrackingservice.domain.IssueLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IssueLogRepo extends JpaRepository<IssueLog, Long> {
    List<IssueLog> findByIssueId(Long issueId);
}
