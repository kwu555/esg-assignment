package com.assignment.assignmentcsvprocessor.task;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
class FileProcessorTest {

    final FileProcessor processor = new FileProcessor();

    @Test
    void testFileExecuteWithSuccess(){
        // given

        // when
        assertTrue(processor.execute());
        // then
    }

    @Test
    void testFileExecuteWithFailure(){
        // given

        // when
        assertFalse(processor.execute());

        // then
    }
}
