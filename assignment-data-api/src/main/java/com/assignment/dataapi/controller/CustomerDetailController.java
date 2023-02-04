package com.assignment.dataapi.controller;

import com.assignment.dataapi.dto.CustomerDto;
import com.assignment.dataapi.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/api/customer")
// could have Swagger for API Specs
public class CustomerDetailController {

    @Autowired
    private CustomerService customerService;

    @PostMapping
    public ResponseEntity<CustomerDto> createNewCustomer(@Valid @RequestBody CustomerDto dto){
        return new ResponseEntity<>(customerService.addCustomer(dto), HttpStatus.CREATED);
    }


    @GetMapping(value="/{customerRef}")
    public ResponseEntity<CustomerDto> getCustomer(@PathVariable(name = "customerRef") String ref){

        CustomerDto result = customerService.findCustomerByRef(ref);

        if(result == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
