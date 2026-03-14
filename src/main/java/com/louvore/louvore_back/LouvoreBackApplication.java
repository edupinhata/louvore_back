package com.louvore.louvore_back;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import com.louvore.louvore_back.security.JwtProperties;

@SpringBootApplication
@EnableConfigurationProperties(JwtProperties.class)
public class LouvoreBackApplication {
    public static void main(String[] args) {
        SpringApplication.run(LouvoreBackApplication.class, args);
    }
}
