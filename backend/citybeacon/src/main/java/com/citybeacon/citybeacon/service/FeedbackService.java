package com.citybeacon.citybeacon.service;

import com.citybeacon.citybeacon.Feedback;
import com.citybeacon.citybeacon.Issue;
import com.citybeacon.citybeacon.repository.FeedbackRepository;
import com.citybeacon.citybeacon.repository.IssueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class FeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepository;

    @Autowired
    private IssueRepository issueRepository;

    public Feedback submitFeedback(Feedback feedback) {
        feedback.setCreatedAt(LocalDateTime.now());
        return feedbackRepository.save(feedback);
    }

    public List<Feedback> getFeedbackByIssue(Long issueId) {
        Issue issue = issueRepository.findById(issueId).orElseThrow();
        return feedbackRepository.findByIssue(issue);
    }
}
