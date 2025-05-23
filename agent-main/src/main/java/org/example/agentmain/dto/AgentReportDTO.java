package org.example.agentmain.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AgentReportDTO {

    private Long id;
    private String reportNumber;
    private LocalDateTime createTime;
    private String docUID;
    private Integer agentLNR;
    private List<PolicyPeriodSnapshotDto> polices;
    private Boolean isESign;
}
