package org.example.agentreport.service;

import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.example.agentreport.dto.AgentReportDto;
import org.example.agentreport.dto.PolicyPeriodSnapshotDto;
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

    @Transactional(readOnly = true)
    public List<AgentReportDto> findAll() {
        return agentReportRepository.findAll().stream()
            .map(this::mapToDto)
            .toList();
    }

    private AgentReportDto mapToDto(AgentReport report) {
        AgentReportDto dto = new AgentReportDto();
        dto.setId(report.getId());
        dto.setReportNumber(report.getReportNumber());
        dto.setCreateTime(report.getCreateTime());
        dto.setDocUID(report.getDocUID());
        dto.setAgentLNR(report.getAgentLNR());
        dto.setIsESign(report.getIsESign());

        // Пример для маппинга полей из полей `polices`
        if (report.getPolices() != null) {
            dto.setPolices(
                report.getPolices().stream()
                    .map(police -> new PolicyPeriodSnapshotDto(
                        police.getId(),
                        police.getPolicyNumber(),
                        police.getReportNumber()))
                    .collect(Collectors.toList())
            );
        }

        return dto;
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

