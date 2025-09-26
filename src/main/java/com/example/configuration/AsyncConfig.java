package com.example.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

@Configuration
@EnableAsync
public class AsyncConfig {
    // Si más adelante querés un pool custom, definí un @Bean TaskExecutor aquí.
}
