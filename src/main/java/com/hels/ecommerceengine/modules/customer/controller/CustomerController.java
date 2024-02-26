package com.hels.ecommerceengine.modules.customer.controller;

import com.hels.ecommerceengine.modules.customer.dto.CreateCustomerAccountDTO;
import com.hels.ecommerceengine.modules.customer.entity.CustomerEntity;
import com.hels.ecommerceengine.modules.customer.service.CreateCustomerAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/customer")
public class CustomerController {

    private final CreateCustomerAccountService createCustomerAccountService;

    @PostMapping
    public CreateCustomerAccountDTO.Response customerCreateAccount (@RequestBody CreateCustomerAccountDTO.Request requestBody) {
        CustomerEntity customer = createCustomerAccountService.execute(requestBody);

        return new CreateCustomerAccountDTO.Response(customer.getId().toString());
    }
}
