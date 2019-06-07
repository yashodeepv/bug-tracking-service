package com.yasho.bugtrackingservice.jpa;

import com.yasho.bugtrackingservice.domain.Project;
import com.yasho.bugtrackingservice.domain.WeeklyIssues;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WeeklyIssuesRepo extends JpaRepository<WeeklyIssues, Long> {
    List<WeeklyIssues> findByWeekAndYear(int week, int year);

    @Query("SELECT p FROM WeeklyIssues p WHERE ((p.week >= :fromweek and p.year = :fromyear) and (p.week <= :toweek and p.year = :toyear)) and p.project = :project and p.type in (:types)")
    public List<WeeklyIssues> find(@Param("fromweek") int fromweek,
                                   @Param("fromyear") int fromyear,
                                   @Param("toweek") int toweek,
                                   @Param("toyear") int toyear,
                                   @Param("project") Project project,
                                   @Param("types") List<String> types);
}
