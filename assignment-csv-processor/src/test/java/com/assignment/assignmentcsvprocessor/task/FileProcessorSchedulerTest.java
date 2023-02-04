package com.assignment.assignmentcsvprocessor.task;

import com.assignment.assignmentcsvprocessor.config.TestSchedulerConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.awaitility.Awaitility.await;
import static org.awaitility.Durations.TWO_SECONDS;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;

@SpringBootTest
@SpringJUnitConfig(TestSchedulerConfig.class)
@TestPropertySource(locations="classpath:application-test.properties")
class FileProcessorSchedulerTest {

    @Autowired
    FileProcessorScheduler scheduler;

    @Test
    void testScheduler(){

        await().atMost(TWO_SECONDS)
                .untilAsserted(() -> verify(scheduler, atLeast(2)).readAndExecute());
    }

}
