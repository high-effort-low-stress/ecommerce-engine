package com.hels.ecommerceengine.modules.customer.service;

import com.hels.ecommerceengine.exceptions.ApiException;
import com.hels.ecommerceengine.modules.customer.entity.CustomerEntity;
import com.hels.ecommerceengine.modules.customer.repository.ICustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class InactiveCustomerService {
    private final ICustomerRepository repository;

    public void execute(Long id) {
        CustomerEntity customer = repository.findById(id).orElse(null);

        if (Objects.isNull(customer))
            throw new ApiException("Customer not found");

        customer.setActive(false);

        repository.save(customer);
    }


}
