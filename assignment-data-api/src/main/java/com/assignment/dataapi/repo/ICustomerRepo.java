package com.assignment.dataapi.repo;

import com.assignment.dataapi.entity.CustomerEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ICustomerRepo extends CrudRepository<CustomerEntity, Integer> {
    Optional<CustomerEntity> findByCustomerRef(String customerRef);
}
