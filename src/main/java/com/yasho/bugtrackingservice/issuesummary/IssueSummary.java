package com.yasho.bugtrackingservice.issuesummary;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IssueSummary {
    private String issueId;
    private String type;
}
