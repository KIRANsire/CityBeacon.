package com.citybeacon.citybeacon.repository;

import com.citybeacon.citybeacon.IssueLog;
import com.citybeacon.citybeacon.Issue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface IssueLogRepository extends JpaRepository<IssueLog, Long> {
    List<IssueLog> findByIssue(Issue issue);
}
