package com.hels.ecommerceengine.modules.customer.service;


import com.hels.ecommerceengine.modules.customer.dto.CreateCustomerAccountDTO;
import com.hels.ecommerceengine.modules.customer.entity.CustomerEntity;
import com.hels.ecommerceengine.modules.customer.mapper.CustomerMapper;
import com.hels.ecommerceengine.modules.customer.repository.ICustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CreateCustomerAccountService {
    private final ICustomerRepository repository;
    private final CustomerMapper mapper;
    public CustomerEntity execute (CreateCustomerAccountDTO dto) {
        CustomerEntity customer = mapper.toEntity(dto);

        return repository.save(customer);
    }
}
