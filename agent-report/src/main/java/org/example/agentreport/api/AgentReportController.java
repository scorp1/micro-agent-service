package org.example.agentreport.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.example.agentreport.dto.AgentReportDto;
import org.example.agentreport.entity.AgentReport;
import org.example.agentreport.service.AgentReportService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/agent-reports")
@RequiredArgsConstructor
@Tag(name = "Agent Reports", description = "API для управления отчётами агентов")
public class AgentReportController {

    private final AgentReportService agentReportService;

    @PostMapping()
    @Operation(summary = "Создать новый отчет агента", description = "Создает новый отчет агента")
    public ResponseEntity<AgentReport> createAgentReport(@RequestBody AgentReport agentReport) {
        if (agentReportService.findByReportNumber(agentReport.getReportNumber()).isPresent()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        //agentReport.setCreateTime(LocalDateTime.now());
        AgentReport createdReport = agentReportService.save(agentReport);

        return new ResponseEntity<>(createdReport, HttpStatus.CREATED);
    }

    @GetMapping("/report-number/{reportNumber}")
    @Operation(summary = "Получить отчёт по номеру отчета", description = "Возвращает отчёт агента по номеру")
    public ResponseEntity<AgentReport> getAgentReportByReportNumber(@PathVariable String reportNumber) {
        Optional<AgentReport> agentReport = agentReportService.findByReportNumber(reportNumber);
        if (agentReport.isPresent()) {
            return new ResponseEntity<>(agentReport.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping
    @Operation(summary = "Получить все отчеты", description = "Возвращает все отчеты")
    public ResponseEntity<List<AgentReportDto>> getAllAgentReports() {
        List<AgentReportDto> agentReports = agentReportService.findAll();
        return new ResponseEntity<>(agentReports, HttpStatus.OK);
    }

    @PutMapping("/{reportNumber}")
    @Operation(summary = "Обновить отчет", description = "Обновляет существующий отчет агент по номеру отчета")
    public ResponseEntity<AgentReport> updateAgentReport(@PathVariable String reportNumber,
                                                         @RequestBody AgentReport agentReport) {
        Optional<AgentReport> report = agentReportService.findByReportNumber(reportNumber);
        if (report.isPresent()) {
            //agentReport.setCreateTime(LocalDateTime.now());
            return new ResponseEntity<>(agentReportService.update(report.get(), agentReport), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{reportNumber}")
    @Operation(summary = "Удалить отчет", description = "Удаляет отчет агента, по номеру отчета")
    public ResponseEntity<Void> deleteAgentReport(@PathVariable String reportNumber) {
        if (agentReportService.findByReportNumber(reportNumber).isPresent()) {
            agentReportService.delete(reportNumber);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}

