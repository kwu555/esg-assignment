package com.assignment.dataapi.repo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Sql("/sql/test-schema.sql")
class ICustomerRepoTest {

    @Autowired
    private ICustomerRepo repo;

    @Test
    void testFindByCustomerRef(){
        // given

        // when then
        assertTrue(repo.findByCustomerRef("123").isPresent());
    }
}
