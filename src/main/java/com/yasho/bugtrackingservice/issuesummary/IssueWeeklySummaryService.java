package com.yasho.bugtrackingservice.issuesummary;

import com.yasho.bugtrackingservice.domain.Project;
import com.yasho.bugtrackingservice.domain.WeeklyIssues;
import com.yasho.bugtrackingservice.jpa.IssueLogRepo;
import com.yasho.bugtrackingservice.jpa.IssueRepo;
import com.yasho.bugtrackingservice.jpa.ProjectRepo;
import com.yasho.bugtrackingservice.jpa.WeeklyIssuesRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.IsoFields;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class IssueWeeklySummaryService {

    private final WeeklyIssuesRepo weeklyIssuesRepo;
    private final IssueRepo issueRepo;
    private final ProjectRepo projectRepo;

    public IssueWeeklySummaryService(WeeklyIssuesRepo weeklyIssuesRepo,
                                     IssueRepo issueRepo,
                                     ProjectRepo projectRepo) {
        this.weeklyIssuesRepo = weeklyIssuesRepo;
        this.issueRepo = issueRepo;
        this.projectRepo = projectRepo;
    }

    public void aggregate() {
        ZonedDateTime now = ZonedDateTime.ofInstant(Instant.now(), ZoneId.systemDefault());
        int week = now.get(IsoFields.WEEK_OF_WEEK_BASED_YEAR);
        int year = now.getYear();
        // delete all summaries entered for current week
        weeklyIssuesRepo.findByWeekAndYear(week, year).forEach(weeklyIssuesRepo::delete);

        // insert fresh
        issueRepo.findAll().stream()
                .map(a -> WeeklyIssues.builder()
                            .issue(a)
                            .project(a.getProject())
                            .state(a.getCurrentState())
                            .week(week)
                            .year(year)
                            .type(a.getType())
                            .build())
                .forEach(weeklyIssuesRepo::saveAndFlush);

        weeklyIssuesRepo.findAll().stream().forEach(System.out::println);
    }

    public Set<IssueWeeklySummary> getIssueWeeklySummaries(IssueSummaryRequest issueSummaryRequest) {
        int fromWeek = Integer.parseInt(issueSummaryRequest.getFromWeek().toLowerCase().split("w")[1]);
        int fromYear = Integer.parseInt(issueSummaryRequest.getFromWeek().toLowerCase().split("w")[0]);
        int toWeek = Integer.parseInt(issueSummaryRequest.getToWeek().toLowerCase().split("w")[1]);
        int toYear = Integer.parseInt(issueSummaryRequest.getToWeek().toLowerCase().split("w")[0]);

        Project project =
                projectRepo.findByProjectId(issueSummaryRequest.getProjectId()).orElseThrow(RuntimeException::new);
        List<WeeklyIssues> weeklyIssues =
                weeklyIssuesRepo.find(fromWeek, fromYear, toWeek, toYear, project, issueSummaryRequest.getTypes());
        Map<String, Map<String, List<IssueSummary>>> weekSummaries = new HashMap<>();

        weeklyIssues.forEach(a -> {
            String weekKey = a.getYear() + "W" + a.getWeek();
            Map<String, List<IssueSummary>> stateSummaries = weekSummaries.get(weekKey);
            if (stateSummaries == null) {
                stateSummaries = new HashMap<>();
            }
            List<IssueSummary> issueSummaries = stateSummaries.get(a.getState());
            if (issueSummaries == null) {
                issueSummaries = new ArrayList<>();
            }
            issueSummaries.add(IssueSummary.builder().type(a.getType()).issueId(a.getIssue().getIssueId()).build());
            stateSummaries.put(a.getState(), issueSummaries);
            weekSummaries.put(weekKey, stateSummaries);
        });


        return weeklyIssues.stream()
                .map(a -> IssueWeeklySummary.builder()
                        .week(a.getYear() + "W" + a.getWeek())
                        .stateSummaries(
                                weekSummaries.get(a.getYear() + "W" + a.getWeek())
                                        .entrySet()
                                        .stream()
                                        .map(b ->
                                                IssueStateSummary.builder()
                                                        .state(b.getKey())
                                                        .count(b.getValue().size())
                                                        .issues(b.getValue())
                                                        .build())
                                        .collect(Collectors.toList()))
                        .build())
                .collect(Collectors.toSet());
    }
}
