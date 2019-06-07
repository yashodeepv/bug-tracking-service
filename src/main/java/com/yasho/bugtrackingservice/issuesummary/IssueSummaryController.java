package com.yasho.bugtrackingservice.issuesummary;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class IssueSummaryController {

    private final IssueWeeklySummaryService summaryService;

    public IssueSummaryController(IssueWeeklySummaryService summaryService) {
        this.summaryService = summaryService;
    }


    @PostMapping("getWeeklySummary")
    public IssueSummaryResponse getSummary(@RequestBody IssueSummaryRequest issueSummaryRequest) {
        Set<IssueWeeklySummary> collect = summaryService.getIssueWeeklySummaries(issueSummaryRequest);
        return IssueSummaryResponse.builder()
                .projectId(issueSummaryRequest.getProjectId())
                .weeklySummaries(new ArrayList<>(collect))
                .build();
    }


}
