package com.yasho.bugtrackingservice.batchjobs;

import com.yasho.bugtrackingservice.issue.IssueStorageService;
import com.yasho.bugtrackingservice.jpa.ProjectRepo;
import com.yasho.bugtrackingservice.thirdpartyissueservice.ThirdPartyIssueService;
import com.yasho.bugtrackingservice.thirdpartyissueservice.ThirdPartyIssueServiceRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class GetIssuesJob {

    private final ThirdPartyIssueService service;
    private final ProjectRepo projectRepo;
    private final IssueStorageService issueStorageService;

    public GetIssuesJob(ThirdPartyIssueService service, ProjectRepo projectRepo, IssueStorageService issueStorageService) {
        this.service = service;
        this.projectRepo = projectRepo;
        this.issueStorageService = issueStorageService;
    }


    // Runs daily
    @Scheduled(fixedRate = 1000*60*60*24)
    public void execute() {
        log.info("Get issues job started ====> ");
        projectRepo.findAll()
                .stream()
                // Add delay before calling for next record
                .map(a -> {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return a;
                })
                .map(a -> service.getIssues(ThirdPartyIssueServiceRequest.builder()
                        .projectId(a.getProjectId())
                        .build()))
                .forEach(issueStorageService::store);

    }
}
