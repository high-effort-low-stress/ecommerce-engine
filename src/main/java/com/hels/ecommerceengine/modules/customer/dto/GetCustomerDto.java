package com.hels.ecommerceengine.modules.customer.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class GetCustomerDto {
    @ToString
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Response {
        String name;
        String document;
        String email;
        String phoneNumber;
        LocalDate birthDate;
    }
}
