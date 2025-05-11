package org.example.agentmain.service;

import lombok.RequiredArgsConstructor;
import org.example.agentmain.client.AgentReportClient;
import org.example.agentmain.dto.AgentReportDTO;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AgentReportClientService {

    private final AgentReportClient agentReportClient;

    public AgentReportDTO[] getAllReports() {
        return agentReportClient.getAllReports();
    }

    public AgentReportDTO createReport(AgentReportDTO agentReport) {
        return agentReportClient.createAgentReport(agentReport);
    }

    public AgentReportDTO getAgentReportByReportNumber(String reportNumber) {
        return agentReportClient.getAgentReportByNumber(reportNumber);
    }

    public AgentReportDTO updateAgentReport(AgentReportDTO agentReport) {
        return agentReportClient.updateAgentReport(agentReport);
    }

    public Void deleteAgentReport(String reportNumber) {
        return agentReportClient.deleteAgentReport(reportNumber);
    }
}
