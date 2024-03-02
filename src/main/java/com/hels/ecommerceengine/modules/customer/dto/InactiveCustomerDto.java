package com.hels.ecommerceengine.modules.customer.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class InactiveCustomerDto {

    @ToString
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Request {
        private Long id;
    }
}
