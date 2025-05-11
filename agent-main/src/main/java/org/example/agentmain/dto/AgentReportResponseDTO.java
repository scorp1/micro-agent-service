package org.example.agentmain.dto;

import java.util.List;

public class AgentReportResponseDTO {
    private String requestId;
    private List<AgentReportDTO> reports;

    // Конструкторы
    public AgentReportResponseDTO() {}

    public AgentReportResponseDTO(String requestId, List<AgentReportDTO> reports) {
        this.requestId = requestId;
        this.reports = reports;
    }

    // Геттеры и сеттеры
    public String getRequestId() { return requestId; }
    public void setRequestId(String requestId) { this.requestId = requestId; }

    public List<AgentReportDTO> getReports() { return reports; }
    public void setReports(List<AgentReportDTO> reports) { this.reports = reports; }

}
