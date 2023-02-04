package com.assignment.assignmentcsvprocessor.task;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.awaitility.Awaitility.await;
import static org.awaitility.Durations.TWO_SECONDS;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
class FileProcessorSchedulerTest {

    @Autowired
    FileProcessorScheduler scheduler;

    @Test
    void testScheduler(){

        await().atMost(TWO_SECONDS)
                .untilAsserted(() -> verify(scheduler, atLeast(2)).readAndExecute());
    }

}
