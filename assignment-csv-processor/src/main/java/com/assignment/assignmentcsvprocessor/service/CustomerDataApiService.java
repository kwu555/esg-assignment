package com.assignment.assignmentcsvprocessor.service;

import com.assignment.assignmentcsvprocessor.dto.CustomerDetailDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
/**
 *  Customer Data API service, responsible for persisting new customer data
 */
public class CustomerDataApiService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${customer.data-api.url}")
    private String dataApiUrl;


    public boolean createNewCustomer(CustomerDetailDto dto){

        try{
            HttpEntity<CustomerDetailDto> entity = new HttpEntity<>(dto);

            restTemplate.exchange(dataApiUrl + "/api/customer", HttpMethod.POST, entity, CustomerDetailDto.class);
            log.info("Customer {} details processed successfully", dto.getCustomerRef());
            return true;

        } catch (HttpClientErrorException ex) {
            if(ex.getStatusCode().equals(HttpStatus.UNPROCESSABLE_ENTITY)){
                // ignore, customer detail already processed
                log.warn("Customer {} has already been processed", dto.getCustomerRef());
                return true;
            }else{
                // continue processing if there is error, is likely to be data related
                log.error("Unable to process customer ref {} with error {}", dto.getCustomerRef(), ex.getMessage());
                return false;
            }
        }catch (HttpServerErrorException e){

            throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());

        }catch (Exception e){
            throw new HttpClientErrorException(HttpStatus.SERVICE_UNAVAILABLE, e.getMessage());
        }
    }
}
