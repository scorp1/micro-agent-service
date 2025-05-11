package org.example.agentmain.client;

import org.example.agentmain.config.FeignConfig;
import org.example.agentmain.dto.AgentReportDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "agent-report", configuration = FeignConfig.class)
public interface AgentReportClient {

    @GetMapping("/api/agent-reports")
    AgentReportDTO[] getAllReports();

    @PostMapping("/api/agent-reports")
    AgentReportDTO createAgentReport(@RequestBody AgentReportDTO agentReport);

    @GetMapping("/api/agent-reports/report-number/{reportNumber")
    AgentReportDTO getAgentReportByNumber(@PathVariable("reportNumber") String reportNumber);

    @PutMapping("/api/agent-reports/{reportNumber")
    AgentReportDTO updateAgentReport(@RequestBody AgentReportDTO agentReportDTO);

    @DeleteMapping("/api/agent-reports/{reportNumber}")
    Void deleteAgentReport(@PathVariable String reportNumber);
}
