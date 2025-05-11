package org.example.policyperiod;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class PolicyPeriodApplication {

    public static void main(String[] args) {
        SpringApplication.run(PolicyPeriodApplication.class, args);
    }

}
