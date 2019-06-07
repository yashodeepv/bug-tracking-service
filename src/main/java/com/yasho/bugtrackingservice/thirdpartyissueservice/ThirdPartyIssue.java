package com.yasho.bugtrackingservice.thirdpartyissueservice;

import com.yasho.bugtrackingservice.entity.IssueState;
import com.yasho.bugtrackingservice.entity.IssueType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ThirdPartyIssue {
    private String issueId;
    private String type;
    private String currentState;
    private List<ThirdPartyIssueLog> changeLogs;
}
