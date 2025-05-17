package org.example.agentreport.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PolicyPeriodSnapshotDto {
  private Long id;
  private String policyNumber;
  private String reportNumber;

}
