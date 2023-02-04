package com.assignment.assignmentcsvprocessor.task;

import com.assignment.assignmentcsvprocessor.dto.CustomerDetailDto;
import com.assignment.assignmentcsvprocessor.exception.CSVFormatException;
import com.assignment.assignmentcsvprocessor.service.CustomerDataApiService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

@Service
@Slf4j
public class FileProcessor {

    @Autowired
    private CustomerDataApiService dataApiService;

    @Value("${file.folder}")
    private String folder;

    private static final String[] HEADERS = { "CUSTOMER_REF", "CUSTOMER_NAME", "CUSTOMER_ADDRESS_1", "CUSTOMER_ADDRESS_2", "TOWN", "COUNTY", "COUNTRY", "POSTCODE"};

    public boolean execute(){

        log.info("Starting to process file");

        for (CSVRecord record : readCSVFile()) {

            try {
                CustomerDetailDto request = CustomerDetailDto.builder().customerRef(record.get("CUSTOMER_REF"))
                        .customerName(record.get("CUSTOMER_NAME"))
                        .addressLineOne(record.get("CUSTOMER_ADDRESS_1"))
                        .addressLineTwo(record.get("CUSTOMER_ADDRESS_2"))
                        .town(record.get("TOWN"))
                        .county(record.get("COUNTY"))
                        .country(record.get("COUNTRY"))
                        .postcode(record.get("POSTCODE")).build();


                // TODO could handle failure case better
                dataApiService.createNewCustomer(request);

            }catch (Exception e){
                log.error("Exception thrown while processing the record with ref [{}], error message - {}", record.get("CUSTOMER_REF"), e.getLocalizedMessage());
                return false;
            }
        }

        // TODO move file to a different location or delete it after is processed
        return true;
    }

    private Iterable<CSVRecord> readCSVFile() {
        // Read file
        BufferedReader in =null;
        try (Stream<Path> walk = Files.walk(Paths.get(folder))) {

            List<String> result = walk.map(Path::toString)
                    .filter(f -> f.endsWith(".csv")).toList();
            log.info("Total number of files in documents [{}]", result.size());
            if(!result.isEmpty()) {
                Path filePath = Paths.get(result.get(0));

                log.info("Reading file [{}]", filePath.getFileName());
                in = Files.newBufferedReader(filePath);

                return CSVFormat.DEFAULT
                        .withHeader(HEADERS)
                        .withFirstRecordAsHeader()
                        .parse(in);
            }

        } catch (IOException e) {
            throw new CSVFormatException("Exception while reading CSV file - error message - {}", e);
        }

        return null;
    }
}
