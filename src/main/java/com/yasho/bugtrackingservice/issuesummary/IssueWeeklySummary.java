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
public class IssueWeeklySummary {
    private String week;
    private List<IssueStateSummary> stateSummaries;
}
