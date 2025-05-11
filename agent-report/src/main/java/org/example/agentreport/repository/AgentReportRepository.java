package org.example.agentreport.repository;

import org.example.agentreport.entity.AgentReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AgentReportRepository extends JpaRepository<AgentReport, Long> {
    Optional<AgentReport> findByReportNumber(String reportNumber);
    void deleteAgentReportByReportNumber(String reportNumber);
}
