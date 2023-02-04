package com.assignment.dataapi.service;

import com.assignment.dataapi.dto.CustomerDto;
import com.assignment.dataapi.entity.CustomerEntity;
import com.assignment.dataapi.exception.ResourceExistException;
import com.assignment.dataapi.repo.ICustomerRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;

@ExtendWith(SpringExtension.class)
class CustomerServiceTest {

    @InjectMocks
    private CustomerService customerService;

    @Mock
    private ICustomerRepo customerRepo;

    @Test
    void testAddCustomer_with_outcome_success(){
        // given
        CustomerDto dto = new CustomerDto();
        dto.setCustomerRef("123");
        CustomerEntity entity = new CustomerEntity();
        entity.setCustomerRef("123");
        given(customerRepo.findByCustomerRef("123")).willReturn(Optional.empty());
        given(customerRepo.save(any())).willReturn(entity);

        // when then
        assertNotNull(customerService.addCustomer(dto));
        Mockito.verify(customerRepo, times(1)).findByCustomerRef("123");
        Mockito.verify(customerRepo, times(1)).save(any());

    }

    @Test
    void testAddCustomer_throw_exception_already_exist(){
        // given
        CustomerDto dto = new CustomerDto();
        dto.setCustomerRef("123");
        CustomerEntity entity = new CustomerEntity();
        entity.setCustomerRef("123");
        given(customerRepo.findByCustomerRef("123")).willReturn(Optional.of(entity));

        // when then

        Assertions.assertThrows(ResourceExistException.class, () -> {
            customerService.addCustomer(dto);
        });
        Mockito.verify(customerRepo, times(1)).findByCustomerRef("123");
        Mockito.verify(customerRepo, times(0)).save(any());
    }
}
