package org.example.agentmain.api;

import lombok.RequiredArgsConstructor;
import org.example.agentmain.dto.AgentReportDTO;
import org.example.agentmain.dto.PolicyPeriodDTO;
import org.example.agentmain.kafka.AgentReportRequestHandler;
import org.example.agentmain.kafka.PolicyPeriodRequestHandler;
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
    private final AgentReportRequestHandler agentReportRequestHandler;
    private final PolicyPeriodRequestHandler policyPeriodRequestHandler;

    @GetMapping("/agent-reports")
    public ResponseEntity<AgentReportDTO[]> getAgentReports() {
        AgentReportDTO[] reports = agentReportClientService.getAllReports();

        return new ResponseEntity<>(reports, HttpStatus.OK);
    }

    @GetMapping("/policy-periods")
    public ResponseEntity<PolicyPeriodDTO[]> getPolicyPeriods() {
        PolicyPeriodDTO[] periods = policyPeriodClientService.getAllPolicyPeriods();

        return new ResponseEntity<>(periods, HttpStatus.OK);
    }

    @PostMapping("/agent-reports")
    public CompletableFuture<AgentReportDTO> createAgentReport(@RequestBody AgentReportDTO agentReportDTO) {

        return agentReportRequestHandler.sendRequest(agentReportDTO);
    }

    @PostMapping("/policy-periods")
    public CompletableFuture<PolicyPeriodDTO> createPolicyPeriod(@RequestBody PolicyPeriodDTO policyPeriodDTO) {

        return policyPeriodRequestHandler.sendRequest(policyPeriodDTO);
    }

//    @GetMapping("/agent-reports-add/{agentLnr}")
//    public ResponseEntity<AgentReportDTO> createAgentReportByAgentLNR(@PathVariable String agentLnr) {
//
//    }

}
