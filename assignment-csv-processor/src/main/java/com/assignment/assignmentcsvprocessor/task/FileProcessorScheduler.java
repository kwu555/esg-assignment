package com.assignment.assignmentcsvprocessor.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Slf4j
@EnableScheduling
public class FileProcessorScheduler {

    @Autowired
    private FileProcessor fileProcessor;

    @Scheduled(cron = "${file.scheduler.cron}")
    public void readAndExecute(){

        log.info("Scheduler triggered - processing file");

        boolean result = fileProcessor.execute(); // could rerun the job if it fails

        log.info("Scheduler finished with {} ", result?"success":"failures");

    }
}
