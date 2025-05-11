package org.example.agentreport.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "policy_period_snapshot")
public class PolicyPeriodSnapShot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "policy_number")
    private String policyNumber;

    @Column(name = "report_number")
    private String reportNumber;

    @ManyToOne
    @JoinColumn(name = "report_id")
    private AgentReport reportId;
}
