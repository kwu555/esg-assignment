package com.assignment.assignmentcsvprocessor.service;

import org.apache.commons.csv.CSVRecord;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
class FileHelperTest {

    FileHelper helper = new FileHelper();

    @Test
    void testReadFileMethod(){
        //given

        // when
        Iterable<CSVRecord> response = helper.readCSVFile();

        // then
        assertNotNull(response);
    }
}
