package com.assignment.dataapi.service;

import com.assignment.dataapi.dto.CustomerDto;
import com.assignment.dataapi.entity.CustomerEntity;
import com.assignment.dataapi.exception.ResourceExistException;
import com.assignment.dataapi.mapper.ICustomerMapper;
import com.assignment.dataapi.repo.ICustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private ICustomerRepo repo;

    public CustomerDto addCustomer(CustomerDto dto){
        if(repo.findByCustomerRef(dto.getCustomerRef()).isPresent()){
            throw new ResourceExistException("Customer Ref already exist");
        }
        CustomerEntity entity = ICustomerMapper.INSTANCE.toEntity(dto);
        return ICustomerMapper.INSTANCE.toDto(repo.save(entity));
    }

    public CustomerDto findCustomerByRef(String customerRef){

        Optional<CustomerEntity> result = repo.findByCustomerRef(customerRef);
        return result.map(ICustomerMapper.INSTANCE::toDto).orElse(null);
    }
}
