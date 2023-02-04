package com.assignment.dataapi.controller;

import com.assignment.dataapi.dto.CustomerDto;
import com.assignment.dataapi.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
class CustomerControllerTest {

    @InjectMocks
    CustomerDetailController controller;

    @Mock
    CustomerService customerService;

    @Test
    void testCreateNewCustomer_with_success_201(){
        // given
        CustomerDto dto = new CustomerDto();
        given(customerService.addCustomer(dto)).willReturn(dto);

        // when
        ResponseEntity<CustomerDto> result = controller.createNewCustomer(dto);

        // then
        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        verify(customerService, times(1)).addCustomer(dto);
    }

    @Test
    void testGetCustomer_with_success_200(){
        // given
        CustomerDto dto = new CustomerDto();
        dto.setCustomerRef("123");
        given(customerService.findCustomerByRef("123")).willReturn(dto);

        // when
        ResponseEntity<CustomerDto> result = controller.getCustomer("123");

        // then
        assertEquals(HttpStatus.OK, result.getStatusCode());
        verify(customerService, times(1)).findCustomerByRef("123");
    }

    @Test
    void testGetCustomer_with_notfound_404(){
        // given;
        given(customerService.findCustomerByRef("123")).willReturn(null);

        // when
        ResponseEntity<CustomerDto> result = controller.getCustomer("123");

        // then
        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
        verify(customerService, times(1)).findCustomerByRef("123");
    }
}
