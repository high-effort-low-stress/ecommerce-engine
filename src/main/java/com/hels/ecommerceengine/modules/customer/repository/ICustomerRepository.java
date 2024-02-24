package com.hels.ecommerceengine.modules.customer.repository;

import com.hels.ecommerceengine.modules.customer.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICustomerRepository extends JpaRepository<CustomerEntity, Long> {
}

