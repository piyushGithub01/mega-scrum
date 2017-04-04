package com.scrum.orchestration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.scrum.common.ScrumCommonConfiguration;

@Configuration
@EnableAutoConfiguration
@ComponentScan
@Import({ ScrumCommonConfiguration.class })
public class ScrumOrchestrationServiceConfiguration {

	public static final ExecutorService SCRUM_ORCHESTRATION_EXECUTOR = Executors.newFixedThreadPool(25);
}
