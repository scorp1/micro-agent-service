package org.example.agentreport.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "agent_report")
public class AgentReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "report_number", nullable = false)
    private String reportNumber;

    @CreationTimestamp
    @Column(name = "create_time", nullable = false)
    private LocalDateTime createTime;

    @Column(name = "doc_uid", nullable = false)
    private String docUID;

    @Column(name = "agent_lnr")
    private Integer agentLNR;

    @OneToMany
    @JoinColumn(name = "policy_period_snapshots")
    private List<PolicyPeriodSnapShot> polices;

    @Column(name = "is_esign")
    private Boolean isESign;

}
