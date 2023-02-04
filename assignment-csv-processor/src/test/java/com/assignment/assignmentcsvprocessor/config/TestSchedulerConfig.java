package com.assignment.assignmentcsvprocessor.config;

import com.assignment.assignmentcsvprocessor.service.CustomerDataApiService;
import com.assignment.assignmentcsvprocessor.task.FileProcessor;
import com.assignment.assignmentcsvprocessor.task.FileProcessorScheduler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

import static org.mockito.Mockito.mock;

@Configuration
@EnableScheduling
public class TestSchedulerConfig {

    @Bean
    FileProcessorScheduler fileProcessorScheduler(){
        return mock(FileProcessorScheduler.class);
    }

    @Bean
    FileProcessor fileProcessor() {
        return mock(FileProcessor.class);
    }

    @Bean
    CustomerDataApiService customerDataApiService(){
        return mock(CustomerDataApiService.class);
    }

    @Bean
    RestTemplate restTemplate(){
        return mock(RestTemplate.class);
    }
}
