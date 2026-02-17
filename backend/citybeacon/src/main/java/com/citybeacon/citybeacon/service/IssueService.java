package com.citybeacon.citybeacon.service;

import com.citybeacon.citybeacon.Issue;
import com.citybeacon.citybeacon.IssueLog;
import com.citybeacon.citybeacon.User;
import com.citybeacon.citybeacon.repository.IssueRepository;
import com.citybeacon.citybeacon.repository.IssueLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class IssueService {

    @Autowired
    private IssueRepository issueRepository;

    @Autowired
    private IssueLogRepository issueLogRepository;

    public Issue reportIssue(Issue issue) {
        issue.setStatus("PENDING");
        issue.setCreatedAt(LocalDateTime.now());
        issue.setUpdatedAt(LocalDateTime.now());
        return issueRepository.save(issue);
    }

    public List<Issue> getAllIssues() {
        return issueRepository.findAll();
    }

    public List<Issue> getIssuesByReporter(User user) {
        return issueRepository.findByReportedBy(user);
    }

    public List<Issue> getIssuesByAssignee(User user) {
        return issueRepository.findByAssignedTo(user);
    }

    public Issue updateIssueStatus(Long issueId, String status, User admin) {
        Issue issue = issueRepository.findById(issueId).orElseThrow();
        issue.setStatus(status);
        issue.setUpdatedAt(LocalDateTime.now());
        return issueRepository.save(issue);
    }

    public IssueLog logAction(Long issueId, User employee, String actionTaken, String imagePath) {
        Issue issue = issueRepository.findById(issueId).orElseThrow();
        issue.setStatus("IN_PROGRESS");
        issue.setUpdatedAt(LocalDateTime.now());
        issueRepository.save(issue);

        IssueLog log = new IssueLog();
        log.setIssue(issue);
        log.setEmployee(employee);
        log.setActionTaken(actionTaken);
        log.setImagePath(imagePath);
        log.setCreatedAt(LocalDateTime.now());
        return issueLogRepository.save(log);
    }
}
