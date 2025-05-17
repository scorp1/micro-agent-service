package org.example.policyperiod.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PolicyPeriodDto {

  private Long id;
  private Long agentId;
  private LocalDateTime createTime;
  private LocalDateTime periodStart;
  private LocalDateTime periodEnd;
  private String productCode;
  private String policyStatus;
  private String policyNumber;
  private Integer agentLNR;
}
