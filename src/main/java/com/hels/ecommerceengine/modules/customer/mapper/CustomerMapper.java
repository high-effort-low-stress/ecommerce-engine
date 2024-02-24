package com.hels.ecommerceengine.modules.customer.mapper;

import com.hels.ecommerceengine.modules.customer.dto.CreateCustomerAccountDTO;
import com.hels.ecommerceengine.modules.customer.entity.CustomerEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    default CreateCustomerAccountDTO.Request toDTO (CustomerEntity entity) {
        if (entity == null)
            return null;
        CreateCustomerAccountDTO.Request dto = new CreateCustomerAccountDTO.Request();
        dto.setName(entity.getName());
        dto.setDocument(entity.getDocument());
        dto.setEmail(entity.getEmail());
        dto.setPassword(entity.getPassword());
        dto.setPhoneNumber(entity.getPhoneNumber());
        return dto;
    }

    CreateCustomerAccountDTO toCustomerDTO(CustomerEntity entity);
    CreateCustomerAccountDTO toCustomerDTO(CreateCustomerAccountDTO.Request request);
    CustomerEntity toEntity (CreateCustomerAccountDTO dto);
}
