package com.hels.ecommerceengine.modules.customer.mapper;

import com.hels.ecommerceengine.modules.customer.dto.CreateCustomerAccountDTO;
import com.hels.ecommerceengine.modules.customer.entity.CustomerEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    CustomerEntity toRequestEntity (CreateCustomerAccountDTO.Request request);
}
