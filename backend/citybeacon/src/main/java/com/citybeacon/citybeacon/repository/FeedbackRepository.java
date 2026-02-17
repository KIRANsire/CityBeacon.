package com.citybeacon.citybeacon.repository;

import com.citybeacon.citybeacon.Feedback;
import com.citybeacon.citybeacon.Issue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
    List<Feedback> findByIssue(Issue issue);
}
