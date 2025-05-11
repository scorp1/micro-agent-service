package org.example.agentmain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PolicyPeriodDTO {
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
