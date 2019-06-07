package com.yasho.bugtrackingservice.domain;

import com.yasho.bugtrackingservice.entity.IssueState;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WeeklyIssues {

    @GeneratedValue
    @Id
    private Long id;

    private int year;
    private int week;

    @OneToOne
    @JoinColumn(name="project_id")
    private Project project;

    private String state;

    @OneToOne
    @JoinColumn(name="issue_id")
    private Issue issue;

    private String type;

}
