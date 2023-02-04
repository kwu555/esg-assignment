package com.assignment.assignmentcsvprocessor.task;

import com.assignment.assignmentcsvprocessor.service.CustomerDataApiService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.HttpServerErrorException;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class FileProcessorTest {

    @InjectMocks
    private FileProcessor processor;

    @Mock
    CustomerDataApiService dataApiService;

    @BeforeEach
    void setup(){
        ReflectionTestUtils.setField(processor, "folder", getClass().getClassLoader().getResource("test.csv").getPath());
    }
    @Test
    void testFileExecuteWithSuccess(){
        // given
        given(dataApiService.createNewCustomer(any())).willReturn(true);

        // when then
        assertTrue(processor.execute());
        Mockito.verify(dataApiService, times(2)).createNewCustomer(any());
    }

    @Test
    void testFileExecuteWithFailure(){
        // given
        given(dataApiService.createNewCustomer(any())).willThrow(HttpServerErrorException.class);

        // when then
        assertFalse(processor.execute());
        Mockito.verify(dataApiService, times(1)).createNewCustomer(any());
    }
}
