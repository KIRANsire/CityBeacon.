package com.citybeacon.citybeacon.controller;

import com.citybeacon.citybeacon.Issue;
import com.citybeacon.citybeacon.IssueLog;
import com.citybeacon.citybeacon.User;
import com.citybeacon.citybeacon.service.IssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/issues")
public class IssueController {

    @Autowired
    private IssueService issueService;

    @PostMapping("/report")
    public ResponseEntity<Issue> reportIssue(@RequestBody Issue issue) {
        return ResponseEntity.ok(issueService.reportIssue(issue));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Issue>> getAllIssues() {
        return ResponseEntity.ok(issueService.getAllIssues());
    }

    @GetMapping("/reporter/{userId}")
    public ResponseEntity<List<Issue>> getMyReportedIssues(@PathVariable Long userId) {
        User user = new User(); user.setId(userId); // Mocking user for simplicity
        return ResponseEntity.ok(issueService.getIssuesByReporter(user));
    }

    @GetMapping("/assignee/{userId}")
    public ResponseEntity<List<Issue>> getMyAssignedIssues(@PathVariable Long userId) {
        User user = new User(); user.setId(userId);
        return ResponseEntity.ok(issueService.getIssuesByAssignee(user));
    }

    @PostMapping("/{issueId}/log")
    public ResponseEntity<IssueLog> logAction(@PathVariable Long issueId, @RequestBody Map<String, Object> payload) {
        Long employeeId = Long.valueOf(payload.get("employeeId").toString());
        String actionTaken = payload.get("actionTaken").toString();
        String imagePath = payload.get("imagePath") != null ? payload.get("imagePath").toString() : null;
        
        User employee = new User(); employee.setId(employeeId);
        return ResponseEntity.ok(issueService.logAction(issueId, employee, actionTaken, imagePath));
    }

    @PutMapping("/{issueId}/status")
    public ResponseEntity<Issue> updateStatus(@PathVariable Long issueId, @RequestBody Map<String, String> payload) {
        String status = payload.get("status");
        // Only Admin should call this in a real app
        return ResponseEntity.ok(issueService.updateIssueStatus(issueId, status, null));
    }
}
