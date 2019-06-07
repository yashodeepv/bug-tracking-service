package com.yasho.bugtrackingservice.thirdpartyissueservice;

import com.yasho.bugtrackingservice.entity.IssueState;
import com.yasho.bugtrackingservice.entity.IssueType;
import com.yasho.bugtrackingservice.util.DateUtils;
import org.springframework.stereotype.Service;

import java.time.Month;
import java.util.Arrays;

import static com.yasho.bugtrackingservice.util.DateUtils.getDate;

@Service
public class MockThirdPartyIssueService implements ThirdPartyIssueService {

    @Override
    public ThirdPartyIssueServiceResponse getIssues(ThirdPartyIssueServiceRequest request) {
        System.out.println("in here");
        return ThirdPartyIssueServiceResponse.builder()
                .projectId(request.getProjectId())
                .issues(Arrays.asList(
                        ThirdPartyIssue.builder()
                                .issueId("issue1")
                                .currentState(IssueState.DEPLOYED.toString())
                                .type(IssueType.BUG.toString())
                                .changeLogs(Arrays.asList(
                                        ThirdPartyIssueLog.builder()
                                                .changedOn(getDate(2019, Month.AUGUST, 9))
                                                .fromState(IssueState.OPEN.toString())
                                                .toState(IssueState.IN_PROGRESS.toString())
                                                .build(),
                                        ThirdPartyIssueLog.builder()
                                                .changedOn(getDate(2019, Month.AUGUST, 10))
                                                .fromState(IssueState.IN_PROGRESS.toString())
                                                .toState(IssueState.DEPLOYED.toString())
                                                .build()
                                ))
                        .build(),
                        ThirdPartyIssue.builder()
                                .issueId("issue2")
                                .currentState(IssueState.DEPLOYED.toString())
                                .type(IssueType.BUG.toString())
                                .changeLogs(Arrays.asList(
                                        ThirdPartyIssueLog.builder()
                                                .changedOn(getDate(2019, Month.AUGUST, 9))
                                                .fromState(IssueState.OPEN.toString())
                                                .toState(IssueState.IN_PROGRESS.toString())
                                                .build(),
                                        ThirdPartyIssueLog.builder()
                                                .changedOn(getDate(2019, Month.AUGUST, 10))
                                                .fromState(IssueState.IN_PROGRESS.toString())
                                                .toState(IssueState.DEPLOYED.toString())
                                                .build()
                                ))
                        .build(),
                        ThirdPartyIssue.builder()
                                .issueId("issue3")
                                .currentState(IssueState.OPEN.toString())
                                .type(IssueType.BUG.toString())
                                .changeLogs(Arrays.asList(
                                        ThirdPartyIssueLog.builder()
                                                .changedOn(getDate(2019, Month.AUGUST, 1))
                                                .fromState(null)
                                                .toState(IssueState.OPEN.toString())
                                                .build()
                                ))
                                .build(),
                        ThirdPartyIssue.builder()
                                .issueId("issue4")
                                .currentState(IssueState.OPEN.toString())
                                .type(IssueType.BUG.toString())
                                .changeLogs(Arrays.asList(
                                        ThirdPartyIssueLog.builder()
                                                .changedOn(getDate(2019, Month.AUGUST, 1))
                                                .fromState(null)
                                                .toState(IssueState.OPEN.toString())
                                                .build()
                                ))
                                .build()
                ))
                .build();
    }
}
