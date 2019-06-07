package com.yasho.bugtrackingservice.domain;

import com.yasho.bugtrackingservice.entity.IssueState;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IssueLog {

    @GeneratedValue
    @Id
    private Long id;

    private Date changedOn;
    private String fromState;
    private String toState;

    @OneToOne
    @JoinColumn(name = "issue_id")
    private Issue issue;
}
