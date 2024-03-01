package com.hels.ecommerceengine.modules.customer.controller;

import com.hels.ecommerceengine.modules.customer.dto.CreateCustomerDto;
import com.hels.ecommerceengine.modules.customer.dto.GetCustomerDto;
import com.hels.ecommerceengine.modules.customer.entity.CustomerEntity;
import com.hels.ecommerceengine.modules.customer.service.CreateCustomerService;
import com.hels.ecommerceengine.modules.customer.service.GetCustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/customer")
public class CustomerController {

    private final CreateCustomerService createCustomerService;
    private final GetCustomerService getCustomerService;

    @PostMapping
    public CreateCustomerDto.Response createAccount(@Valid @RequestBody CreateCustomerDto.Request requestBody) {
        CustomerEntity customer = createCustomerService.execute(requestBody);

        return new CreateCustomerDto.Response(customer.getId().toString());
    }

    @GetMapping("/{id}")
    public GetCustomerDto.Response getCustomer (@PathVariable Long id) {
        CustomerEntity customer = getCustomerService.execute(id);

        return new GetCustomerDto.Response(customer.getName(), customer.getDocument(),
                customer.getEmail(), customer.getPhoneNumber(), customer.getBirthDate());
    }
}
