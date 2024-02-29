package com.hels.ecommerceengine.modules.customer.controller;

import com.hels.ecommerceengine.modules.customer.dto.CreateCustomerDto;
import com.hels.ecommerceengine.modules.customer.entity.CustomerEntity;
import com.hels.ecommerceengine.modules.customer.service.CreateCustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/customer")
public class CustomerController {

    private final CreateCustomerService createCustomerService;

    @PostMapping
    public CreateCustomerDto.Response customerCreateAccount (@Valid @RequestBody CreateCustomerDto.Request requestBody) {
        CustomerEntity customer = createCustomerService.execute(requestBody);

        return new CreateCustomerDto.Response(customer.getId().toString());
    }
}
