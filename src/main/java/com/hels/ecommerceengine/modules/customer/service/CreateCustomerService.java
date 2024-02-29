package com.hels.ecommerceengine.modules.customer.service;


import com.hels.ecommerceengine.exceptions.ApiException;
import com.hels.ecommerceengine.modules.customer.dto.CreateCustomerDto;
import com.hels.ecommerceengine.modules.customer.entity.CustomerEntity;
import com.hels.ecommerceengine.modules.customer.mapper.CustomerMapper;
import com.hels.ecommerceengine.modules.customer.repository.ICustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CreateCustomerService {
    private final ICustomerRepository repository;
    private final CustomerMapper mapper;
    public CustomerEntity execute (CreateCustomerDto.Request dto) {
        findDuplicates(dto.getDocument(), dto.getEmail(), dto.getPhoneNumber());
        validateLegalAge(dto.getBirthDate());

        CustomerEntity customer = mapper.toEntity(dto);

        return repository.save(customer);
    }

    private void validateLegalAge(LocalDate birthDate) {
        LocalDate currentDate = LocalDate.now();

        if (Objects.isNull(birthDate))
            throw new RuntimeException("Birth date can't be null");

        if (Period.between(birthDate, currentDate).getYears() < 18)
            throw new ApiException("Customer must be 18+ years old");
    }

    private void findDuplicates(String document, String email, String phoneNumber)  {
        Optional<CustomerEntity> customers = repository.findByDocumentAndEmailAndPhoneNumber(document, email,
                phoneNumber);
        if (customers.isPresent())
            throw new ApiException("Customer already registered");
    }

}
