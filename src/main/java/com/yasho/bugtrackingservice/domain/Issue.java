package com.yasho.bugtrackingservice.domain;

import com.yasho.bugtrackingservice.entity.IssueState;
import com.yasho.bugtrackingservice.entity.IssueType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(uniqueConstraints={@UniqueConstraint(columnNames = {"issueId" , "project_id"})})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Issue {
    @GeneratedValue
    @Id
    private Long id;

    private String issueId;

    private String type;

    @OneToOne
    @JoinColumn(name = "project_id")
    private Project project;

    private String currentState;
}
