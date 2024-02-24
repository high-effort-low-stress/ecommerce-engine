package com.hels.ecommerceengine.modules.customer.dto;


import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class CreateCustomerAccountDTO {

    @ToString
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Request {
        String name;
        String document;
        String email;
        String password;
        String phoneNumber;
        LocalDate birthDate;
    }
    @ToString
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Response {
        private String id;

    }
}
