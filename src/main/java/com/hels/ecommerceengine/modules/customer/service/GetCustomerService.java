package com.hels.ecommerceengine.modules.customer.service;

import com.hels.ecommerceengine.exceptions.ApiException;
import com.hels.ecommerceengine.modules.customer.dto.GetCustomerDto;
import com.hels.ecommerceengine.modules.customer.entity.CustomerEntity;
import com.hels.ecommerceengine.modules.customer.mapper.CustomerMapper;
import com.hels.ecommerceengine.modules.customer.repository.ICustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@RequiredArgsConstructor
@Service
public class GetCustomerService {
    private final ICustomerRepository repository;
    private final CustomerMapper mapper;
    public CustomerEntity execute (Long id) {
        CustomerEntity customer = repository.findById(id).orElse(null);

        if (Objects.isNull(customer))
            throw new ApiException("Customer not found");

        GetCustomerDto.Response response = mapper.entityToGetCustomerDtoResponse(customer);

        return mapper.getCustomerDtoResponseToEntity(response);
    }

}
