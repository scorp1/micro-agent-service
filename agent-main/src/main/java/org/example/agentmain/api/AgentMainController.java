package org.example.agentmain.api;

import lombok.RequiredArgsConstructor;
import org.example.agentmain.dto.AgentReportDTO;
import org.example.agentmain.dto.PolicyPeriodDTO;
import org.example.agentmain.kafka.AgentReportCreateRequestHandler;
import org.example.agentmain.kafka.AgentReportGetRequestHandler;
import org.example.agentmain.kafka.PolicyPeriodCreateRequestHandler;
import org.example.agentmain.kafka.PolicyPeriodGetRequestHandler;
import org.example.agentmain.service.AgentReportClientService;
import org.example.agentmain.service.PolicyPeriodClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/agent-main")
public class AgentMainController {

    private final AgentReportClientService agentReportClientService;
    private final PolicyPeriodClientService policyPeriodClientService;
    private final AgentReportCreateRequestHandler agentReportRequestHandler;
    private final PolicyPeriodCreateRequestHandler policyPeriodCreateRequestHandler;
    private final AgentReportGetRequestHandler agentReportGetRequestHandler;
    private final PolicyPeriodGetRequestHandler policyPeriodGetRequestHandler;

    @GetMapping("/agent-reports")
    public CompletableFuture<String> getAgentReports() {

        var agentReports = agentReportGetRequestHandler.sendRequest();

        return agentReports;
    }

    @GetMapping("/policy-periods")
    public CompletableFuture<String> getPolicyPeriods() {
        var periods = policyPeriodGetRequestHandler.sendRequest();

        return periods;
    }

    @PostMapping("/agent-report")
    public CompletableFuture<AgentReportDTO> createAgentReport(@RequestBody AgentReportDTO agentReportDTO) {

        return agentReportRequestHandler.sendRequest(agentReportDTO);
    }

    @PostMapping("/policy-period")
    public CompletableFuture<PolicyPeriodDTO> createPolicyPeriod(@RequestBody PolicyPeriodDTO policyPeriodDTO) {

        return policyPeriodCreateRequestHandler.sendRequest(policyPeriodDTO);
    }

//    @GetMapping("/agent-reports-add/{agentLnr}")
//    public ResponseEntity<AgentReportDTO> createAgentReportByAgentLNR(@PathVariable String agentLnr) {
//
//    }

}
