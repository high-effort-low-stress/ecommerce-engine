package com.hels.ecommerceengine.modules.customer.repository;

import com.hels.ecommerceengine.modules.customer.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ICustomerRepository extends JpaRepository<CustomerEntity, Long> {

    @Query(
            value = "SELECT * FROM ecommerce.customer " +
            "WHERE document=:document AND email=:email AND phone_number=:phoneNumber AND is_active=true",
            nativeQuery = true
    )
    Optional<CustomerEntity> findByDocumentAndEmailAndPhoneNumber(
            @Param("document") String document,
            @Param("email") String email,
            @Param("phoneNumber") String phoneNumber
    );

    @Query(
            value = "SELECT * FROM ecommerce.customer " +
                    "WHERE id=:id AND isActive=true",
            nativeQuery = true
    )
    Optional<CustomerEntity> inactiveById(
            @Param("id") Long id
    );

}

