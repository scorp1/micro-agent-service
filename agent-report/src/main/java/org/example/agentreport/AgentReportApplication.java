package org.example.agentreport;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class AgentReportApplication {

    public static void main(String[] args) {
        SpringApplication.run(AgentReportApplication.class, args);
    }

}
