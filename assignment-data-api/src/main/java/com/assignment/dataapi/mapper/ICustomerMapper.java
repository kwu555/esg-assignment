package com.assignment.dataapi.mapper;

import com.assignment.dataapi.dto.CustomerDto;
import com.assignment.dataapi.entity.CustomerEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
@Mapper(componentModel = "spring")
public interface ICustomerMapper {

    ICustomerMapper INSTANCE = Mappers.getMapper(ICustomerMapper.class);

    CustomerDto toDto(CustomerEntity entity);

    CustomerEntity toEntity(CustomerDto dto);

}
