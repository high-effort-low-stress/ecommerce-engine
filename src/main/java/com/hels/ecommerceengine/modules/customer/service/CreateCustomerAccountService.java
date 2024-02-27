package com.hels.ecommerceengine.modules.customer.service;


import com.hels.ecommerceengine.modules.customer.dto.CreateCustomerAccountDTO;
import com.hels.ecommerceengine.modules.customer.entity.CustomerEntity;
import com.hels.ecommerceengine.modules.customer.mapper.CustomerMapper;
import com.hels.ecommerceengine.modules.customer.repository.ICustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;

@RequiredArgsConstructor
@Service
public class CreateCustomerAccountService {
    private final ICustomerRepository repository;
    private final CustomerMapper mapper;
    public CustomerEntity execute (CreateCustomerAccountDTO.Request dto) {
        return repository.save(mapper.toRequestEntity(dto));
    }

    private void validateLegalAge(LocalDate birthDate) {
        LocalDate currentDate = LocalDate.now();

        if (Objects.isNull(birthDate))
            throw new RuntimeException("Birth date can't be null");

        if (Period.between(birthDate, currentDate).getYears() < 18)
            throw new ApiException("User must be 18+ years old");
    }

}
