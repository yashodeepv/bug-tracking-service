package com.yasho.bugtrackingservice.issuesummary;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IssueSummaryResponse {
    private String projectId;
    private List<IssueWeeklySummary> weeklySummaries;


}
