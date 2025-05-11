package org.example.agentreport.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "agent_report_esignature")
public class AgentReportESignature {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "doc_uid")
    private String docUID;

    @OneToOne(mappedBy = "agentReportESignature", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private AgentReport agentReport;

}
