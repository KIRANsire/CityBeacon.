package com.citybeacon.citybeacon.repository;

import com.citybeacon.citybeacon.Issue;
import com.citybeacon.citybeacon.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface IssueRepository extends JpaRepository<Issue, Long> {
    List<Issue> findByReportedBy(User user);
    List<Issue> findByAssignedTo(User user);
    List<Issue> findByStatus(String status);
}
