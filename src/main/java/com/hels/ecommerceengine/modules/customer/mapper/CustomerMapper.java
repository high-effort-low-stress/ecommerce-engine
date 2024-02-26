package com.hels.ecommerceengine.modules.customer.mapper;

import com.hels.ecommerceengine.modules.customer.dto.CreateCustomerAccountDTO;
import com.hels.ecommerceengine.modules.customer.entity.CustomerEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);
    CreateCustomerAccountDTO toDto (CustomerEntity entity);
    CustomerEntity toRequestEntity (CreateCustomerAccountDTO.Request request);
    CustomerEntity toResponseEntity (CreateCustomerAccountDTO.Response response);
    CustomerEntity toEntity (CreateCustomerAccountDTO dto);
}
