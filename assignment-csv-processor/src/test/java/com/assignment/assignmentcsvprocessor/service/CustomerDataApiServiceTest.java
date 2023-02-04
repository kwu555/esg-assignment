package com.assignment.assignmentcsvprocessor.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(SpringExtension.class)
class CustomerDataApiServiceTest {

    @InjectMocks
    private CustomerDataApiService customerService;


    @Test
    void testCustomerDataApi_with_success_response_201(){

        //given

        // when
        CustomerDto response = customerService.createNewCustomer(CustomerDto dto);

        // then
        assertNotNull(response);
    }

    @Test
    void testCustomerDataApi_with_failure_response_422(){


        //given

        // when
        CustomerDto response = customerService.createNewCustomer(CustomerDto dto);

        // then
        assertNull(response);
    }
}
