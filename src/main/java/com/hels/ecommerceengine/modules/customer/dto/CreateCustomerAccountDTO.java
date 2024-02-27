package com.hels.ecommerceengine.modules.customer.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

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
        @NotBlank(message = "name must be provided")
        String name;
        @NotBlank(message = "cpf must be provided")
        @CPF(message = "cpf must be valid")
        String document;
        @NotBlank(message = "email must be provided")
        @Email(message = "email not valid")
        String email;
        @ToString.Exclude
        @NotBlank(message = "password must be provided")
        String password;
        @NotBlank(message = "phone number must be provided")
        @Pattern(regexp = "55\\d{2}9\\d{8}", message = "Phone number must be valid.")
        String phoneNumber;
        @NotNull(message = "birth date must be provided")
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
