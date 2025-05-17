package org.example.agentreport.dto;

import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AgentReportDto {

  private Long id;
  private String reportNumber;
  private LocalDateTime createTime;
  private String docUID;
  private Integer agentLNR;
  private List<PolicyPeriodSnapshotDto> polices;
  private Boolean isESign;
}
