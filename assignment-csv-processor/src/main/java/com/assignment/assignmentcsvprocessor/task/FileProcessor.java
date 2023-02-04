package com.assignment.assignmentcsvprocessor.task;

import com.assignment.assignmentcsvprocessor.dto.CustomerDetailDto;
import com.assignment.assignmentcsvprocessor.service.CustomerDataApiService;
import com.assignment.assignmentcsvprocessor.service.FileLocator;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class FileProcessor {

    @Autowired
    private CustomerDataApiService dataApiService;

    @Autowired
    private FileLocator fileLocator;

    public boolean execute(){

        Iterable<CSVRecord> records = fileLocator.readCSVFile();
        log.info("Starting to process file");

        for (CSVRecord record : records) {

            try {
                CustomerDetailDto request = CustomerDetailDto.builder().customerRef(record.get("CUSTOMER_REF"))
                        .customerName(record.get("CUSTOMER_NAME"))
                        .addressLineOne(record.get("CUSTOMER_ADDRESS_1"))
                        .addressLineTwo(record.get("CUSTOMER_ADDRESS_2"))
                        .town(record.get("TOWN"))
                        .county(record.get("COUNTY"))
                        .country(record.get("COUNTRY"))
                        .postcode(record.get("POSTCODE")).build();


                dataApiService.createNewCustomer(request);

            }catch (Exception e){
                log.error("Exception thrown while processing the record with ref [{}], error message - {}", record.get("CUSTOMER_REF"), e.getLocalizedMessage());
                return false;
            }
        }

        // TODO move file to a different location or delete it after is processed
        return true;
    }
}
