package com.hels.ecommerceengine.modules.customer.controller;

import com.hels.ecommerceengine.modules.customer.dto.CreateCustomerAccountDTO;
import com.hels.ecommerceengine.modules.customer.entity.CustomerEntity;
import com.hels.ecommerceengine.modules.customer.mapper.CustomerMapper;
import com.hels.ecommerceengine.modules.customer.repository.ICustomerRepository;
import com.hels.ecommerceengine.modules.customer.service.CreateCustomerAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/customer")
public class CustomerController {
    private final ICustomerRepository repository;
    private final CreateCustomerAccountService createCustomerAccountService;
    private final CustomerMapper mapper;

//    @PostMapping
//    public CreateCustomerAccountDTO.Response customerCreateAccount (@RequestBody CreateCustomerAccountDTO.Request requestBody) {
//        CustomerEntity customer = createCustomerAccountService.execute(mapper.toCustomerDTO(requestBody));
//
//        return new CreateCustomerAccountDTO.Response(customer.getId().toString());
//    }

    @GetMapping("/all")
    public List<CustomerEntity> findAll () {
        return repository.findAll();
    }
}
