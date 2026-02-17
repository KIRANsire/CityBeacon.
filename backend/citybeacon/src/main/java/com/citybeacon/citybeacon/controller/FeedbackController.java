package com.citybeacon.citybeacon.controller;

import com.citybeacon.citybeacon.Feedback;
import com.citybeacon.citybeacon.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/feedback")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @PostMapping("/submit")
    public ResponseEntity<Feedback> submitFeedback(@RequestBody Feedback feedback) {
        return ResponseEntity.ok(feedbackService.submitFeedback(feedback));
    }

    @GetMapping("/issue/{issueId}")
    public ResponseEntity<List<Feedback>> getFeedbackByIssue(@PathVariable Long issueId) {
        return ResponseEntity.ok(feedbackService.getFeedbackByIssue(issueId));
    }
}
