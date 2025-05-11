package org.example.agentreport.service;

import lombok.RequiredArgsConstructor;
import org.example.agentreport.entity.AgentReport;
import org.example.agentreport.repository.AgentReportRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AgentReportService {
    private final AgentReportRepository agentReportRepository;
    public Optional<AgentReport> findByReportNumber(String reportNumber) {
        if (reportNumber != null && reportNumber != "") {
            return agentReportRepository.findByReportNumber(reportNumber);
        }
        return Optional.of(new AgentReport());
    }

    public AgentReport save(AgentReport agentReport) {

        return agentReportRepository.saveAndFlush(agentReport);
    }

    public List<AgentReport> findAll() {
        return agentReportRepository.findAll();
    }


    @Transactional(propagation = Propagation.REQUIRED)
    public AgentReport update(AgentReport oldAgentReport, AgentReport agentReport) {
        //oldAgentReport.setCreateTime(agentReport.getCreateTime());
        oldAgentReport.setAgentLNR(agentReport.getAgentLNR());
        oldAgentReport.setDocUID(agentReport.getDocUID());
        return agentReportRepository.save(oldAgentReport);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(String reportNumber) {
        agentReportRepository.deleteAgentReportByReportNumber(reportNumber);
    }
}

