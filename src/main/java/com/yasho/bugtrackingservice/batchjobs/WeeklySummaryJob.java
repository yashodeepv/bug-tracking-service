package com.yasho.bugtrackingservice.batchjobs;

import com.yasho.bugtrackingservice.issuesummary.IssueWeeklySummaryService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class WeeklySummaryJob {

    private final IssueWeeklySummaryService issueWeeklySummaryService;

    public WeeklySummaryJob(IssueWeeklySummaryService issueWeeklySummaryService) {
        this.issueWeeklySummaryService = issueWeeklySummaryService;
    }

    // Runs weekly
    @Scheduled(fixedRate = 1000*60*60*24*7)
    public void execute() {
        issueWeeklySummaryService.aggregate();
    }
}
