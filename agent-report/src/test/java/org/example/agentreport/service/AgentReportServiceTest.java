package org.example.agentreport.service;

import org.example.agentreport.entity.AgentReport;
import org.example.agentreport.repository.AgentReportRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class AgentReportServiceTest {
    @Mock
    private AgentReportRepository repository;

    @InjectMocks
    private AgentReportService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

//    @Test
//    void findAll_shouldReturnAllAgentReports() {
//        // Подготавливаем тестовые данные
//        List<AgentReport> reports = Arrays.asList(
//                new AgentReport(1L, "12345", LocalDateTime.now(), "DOC123", 101),
//                new AgentReport(2L, "67890", LocalDateTime.now(), "DOC456", 102)
//        );
//
//        when(repository.findAll()).thenReturn(reports);
//
//        List<AgentReport> result = service.findAll();
//
//        assertEquals(2, result.size());
//        verify(repository, times(1)).findAll();
//    }

    @Test
    void findById_shouldReturnAgentReportWhenExists() {
        AgentReport report = new AgentReport(1L, "12345", LocalDateTime.now(), "DOC123", 101);

        when(repository.findByReportNumber("12345")).thenReturn(Optional.of(report));

        Optional<AgentReport> result = service.findByReportNumber("12345");

        assertTrue(result.isPresent());
        assertEquals("12345", result.get().getReportNumber());
        verify(repository, times(1)).findByReportNumber("12345");
    }

    @Test
    void save_shouldSaveAndReturnAgentReport() {
        AgentReport report = new AgentReport(1L, "12345", LocalDateTime.now(), "DOC123", 101);
        AgentReport savedReport = new AgentReport(1L, "12345", LocalDateTime.now(), "DOC123", 101);

        when(repository.saveAndFlush(report)).thenReturn(savedReport);

        AgentReport result = service.save(report);

        assertNotNull(result.getId());
        assertEquals("12345", result.getReportNumber());
        verify(repository, times(1)).saveAndFlush(report);
    }

}
