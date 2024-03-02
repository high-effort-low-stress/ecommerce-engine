package com.hels.ecommerceengine.modules.customer.controller;

import com.hels.ecommerceengine.modules.customer.dto.CreateCustomerDto;
import com.hels.ecommerceengine.modules.customer.dto.GetCustomerDto;
import com.hels.ecommerceengine.modules.customer.entity.CustomerEntity;
import com.hels.ecommerceengine.modules.customer.mapper.CustomerMapper;
import com.hels.ecommerceengine.modules.customer.service.CreateCustomerService;
import com.hels.ecommerceengine.modules.customer.service.GetCustomerService;
import com.hels.ecommerceengine.modules.customer.service.InactiveCustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/customer")
public class CustomerController {

    private final CreateCustomerService createCustomerService;
    private final GetCustomerService getCustomerService;
    private final CustomerMapper mapper;
    private final InactiveCustomerService inactiveCustomerService;
    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public CreateCustomerDto.Response createAccount(@Valid @RequestBody CreateCustomerDto.Request requestBody) {
        CustomerEntity customer = createCustomerService.execute(requestBody);

        return new CreateCustomerDto.Response(customer.getId().toString());
    }

    @GetMapping("/{id}")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public GetCustomerDto.Response getCustomer (@PathVariable("id") Long id) {
        CustomerEntity customer = getCustomerService.execute(id);

        return mapper.entityToGetCustomerDtoResponse(customer);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void inactiveCustomer (@PathVariable("id") Long id) {
        inactiveCustomerService.execute(id);
    }

}
