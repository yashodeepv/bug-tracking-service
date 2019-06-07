package com.yasho.bugtrackingservice;

import com.yasho.bugtrackingservice.domain.*;
import com.yasho.bugtrackingservice.entity.IssueState;
import com.yasho.bugtrackingservice.entity.IssueType;
import com.yasho.bugtrackingservice.jpa.IssueLogRepo;
import com.yasho.bugtrackingservice.jpa.IssueRepo;
import com.yasho.bugtrackingservice.jpa.ProjectRepo;
import com.yasho.bugtrackingservice.jpa.WeeklyIssuesRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.util.Date;

import static com.yasho.bugtrackingservice.util.DateUtils.getDate;

@SpringBootApplication
@EnableScheduling
public class BugTrackingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BugTrackingServiceApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(IssueRepo issueRepo,
											   IssueLogRepo issueLogRepo,
											   ProjectRepo projectRepo,
											   WeeklyIssuesRepo weeklyIssuesRepo) {
		return a -> {
			Project project1 = projectRepo.saveAndFlush(Project.builder()
					.name("project 1")
					.projectId("project1")
					.build());


		};
	}

}
