package com.hels.ecommerceengine.modules.customer.mapper;

import com.hels.ecommerceengine.modules.customer.dto.CreateCustomerAccountDTO;
import com.hels.ecommerceengine.modules.customer.entity.CustomerEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    default CreateCustomerAccountDTO.Request toEntity (CustomerEntity entity) {
        if (entity == null)
            return null;
        CreateCustomerAccountDTO.Request dto = new CreateCustomerAccountDTO.Request();
        dto.setName(entity.getName());
        dto.setDocument(entity.getDocument());
        dto.setEmail(entity.getEmail());
        dto.setPassword(entity.getPassword());
        dto.setBirthDate(entity.getBirthDate());
        dto.setPhoneNumber(entity.getPhoneNumber());
        return dto;
    }
    CustomerEntity toEntity(CreateCustomerAccountDTO.Request request);
}
