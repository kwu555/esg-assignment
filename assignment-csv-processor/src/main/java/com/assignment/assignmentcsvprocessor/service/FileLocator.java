package com.assignment.assignmentcsvprocessor.service;

import com.assignment.assignmentcsvprocessor.exception.CSVFormatException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
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
public class FileLocator {

    String[] HEADERS = { "CUSTOMER_REF", "CUSTOMER_NAME", "CUSTOMER_ADDRESS_1", "CUSTOMER_ADDRESS_2", "TOWN", "COUNTY", "COUNTRY", "POSTCODE"};

    @Value("${file.folder}")
    private String inputFolder;

    public Iterable<CSVRecord> readCSVFile() {
        // Read file
        BufferedReader in =null;
        try (Stream<Path> walk = Files.walk(Paths.get(inputFolder))) {

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
