package com.hels.ecommerceengine.customer.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "customer", schema = "ecommerce")
public class CustomerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name", length = 55, nullable = false)
    private String name;
    @Column(name = "document", length = 11, nullable = false)
    private String document;
    @Column(name = "email", length = 30, nullable = false)
    private String email;
    @Column(name = "password", length = 20, nullable = false)
    private String password;
    @Column(name = "phone_number", length = 13, nullable = false)
    private String phoneNumber;
    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;
    @Column(name = "is_active", nullable = false)
    private boolean isActive = true;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
    private List<AddressesEntity> addresses;
}
