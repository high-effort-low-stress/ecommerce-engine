package com.hels.ecommerceengine.modules.customer.mapper;

import com.hels.ecommerceengine.modules.customer.dto.CreateCustomerDto;
import com.hels.ecommerceengine.modules.customer.dto.GetCustomerDto;
import com.hels.ecommerceengine.modules.customer.entity.CustomerEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    CustomerEntity toEntity (CreateCustomerDto.Request request);

    GetCustomerDto.Response entityToGetCustomerDtoResponse (CustomerEntity entity);
}
