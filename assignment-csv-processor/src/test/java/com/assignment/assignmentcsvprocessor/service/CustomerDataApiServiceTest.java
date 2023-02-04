package com.assignment.assignmentcsvprocessor.service;

import com.assignment.assignmentcsvprocessor.dto.CustomerDetailDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(SpringExtension.class)
class CustomerDataApiServiceTest {

    @InjectMocks
    private CustomerDataApiService customerService;

    @Mock
    private RestTemplate restTemplate;

    @Test
    void testCustomerDataApi_with_success_response_201(){

        //given
        CustomerDetailDto request = CustomerDetailDto.builder().build();
        final ResponseEntity<CustomerDetailDto> response = new ResponseEntity<>(request, HttpStatus.CREATED);
        given(this.restTemplate.exchange(anyString(), eq(HttpMethod.POST), any(HttpEntity.class), eq(CustomerDetailDto.class))).willReturn(response);

        // when then
        assertTrue(customerService.createNewCustomer(request));
    }

    @Test
    void testCustomerDataApi_with_success_response_422_already_created(){

        //given
        CustomerDetailDto request = CustomerDetailDto.builder().customerRef("1").build();
        HttpClientErrorException ex = new HttpClientErrorException(HttpStatus.UNPROCESSABLE_ENTITY, "invalid");
        given(this.restTemplate.exchange(anyString(), eq(HttpMethod.POST), any(HttpEntity.class), eq(CustomerDetailDto.class))).willThrow(ex);

        // when then
        assertTrue(customerService.createNewCustomer(request));
    }

    @Test
    void testCustomerDataApi_with_failures_data_error(){

        //given
        CustomerDetailDto request = CustomerDetailDto.builder().customerRef("1").build();
        HttpClientErrorException ex = new HttpClientErrorException(HttpStatus.BAD_REQUEST, "invalid");
        given(this.restTemplate.exchange(anyString(), eq(HttpMethod.POST), any(HttpEntity.class), eq(CustomerDetailDto.class))).willThrow(ex);

        // when then
        assertFalse(customerService.createNewCustomer(request));
    }

    @Test
    void testCustomerDataApi_with_exception_server_unavailable(){

        //given
        CustomerDetailDto request = CustomerDetailDto.builder().customerRef("1").build();
        given(this.restTemplate.exchange(anyString(), eq(HttpMethod.POST), any(HttpEntity.class), eq(CustomerDetailDto.class))).willThrow(HttpServerErrorException.class);

        // when then
        Assertions.assertThrows(HttpServerErrorException.class, () -> {
            customerService.createNewCustomer(request);
        });
    }
}
