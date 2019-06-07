package com.yasho.bugtrackingservice.issue;

import com.yasho.bugtrackingservice.domain.Issue;
import com.yasho.bugtrackingservice.domain.IssueLog;
import com.yasho.bugtrackingservice.domain.Project;
import com.yasho.bugtrackingservice.jpa.IssueLogRepo;
import com.yasho.bugtrackingservice.jpa.IssueRepo;
import com.yasho.bugtrackingservice.jpa.ProjectRepo;
import com.yasho.bugtrackingservice.thirdpartyissueservice.ThirdPartyIssueServiceResponse;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class IssueStorageService {

    private final ProjectRepo projectRepo;
    private final IssueRepo issueRepo;
    private final IssueLogRepo issueLogRepo;

    public IssueStorageService(ProjectRepo projectRepo, IssueRepo issueRepo, IssueLogRepo issueLogRepo) {
        this.projectRepo = projectRepo;
        this.issueRepo = issueRepo;
        this.issueLogRepo = issueLogRepo;
    }

    public void store(ThirdPartyIssueServiceResponse issue) {
        String projectId = issue.getProjectId();
        Project project = projectRepo.findByProjectId(projectId).orElseThrow(() -> new RuntimeException());
        issue.getIssues().forEach(a -> {
            Optional<Issue> byProjectIdAndIssueId = issueRepo.findByProjectIdAndIssueId(project.getId(), a.getIssueId());
            Issue issue1 = byProjectIdAndIssueId
                    .orElseGet(() -> issueRepo.saveAndFlush(Issue.builder()
                            .type(a.getType())
                            .project(project)
                            .currentState(a.getCurrentState())
                            .issueId(a.getIssueId())
                            .build()));
            // Remove all old logs of the issue -
            // ASSUMPTION that getIssue service always return entire log
            issueLogRepo.findByIssueId(issue1.getId())
                    .forEach(issueLogRepo::delete);
            a.getChangeLogs().forEach(il -> issueLogRepo.saveAndFlush(
                    IssueLog.builder()
                            .toState(il.getToState())
                            .fromState(il.getFromState())
                            .changedOn(il.getChangedOn())
                            .issue(issue1)
                            .build()
            ));
        });
        projectRepo.findAll().stream().forEach(System.out::println);
        issueRepo.findAll().stream().forEach(System.out::println);
        issueLogRepo.findAll().stream().forEach(System.out::println);

    }
}
