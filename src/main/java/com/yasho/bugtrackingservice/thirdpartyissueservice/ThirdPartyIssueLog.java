package com.yasho.bugtrackingservice.thirdpartyissueservice;

import com.yasho.bugtrackingservice.entity.IssueState;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ThirdPartyIssueLog {
    private Date changedOn;
    private String fromState;
    private String toState;
}
