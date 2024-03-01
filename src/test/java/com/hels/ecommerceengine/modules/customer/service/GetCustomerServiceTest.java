package com.hels.ecommerceengine.modules.customer.service;

import com.hels.ecommerceengine.exceptions.ApiException;
import com.hels.ecommerceengine.modules.customer.entity.CustomerEntity;
import com.hels.ecommerceengine.modules.customer.mapper.CustomerMapper;
import com.hels.ecommerceengine.modules.customer.repository.ICustomerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
public class GetCustomerServiceTest {
    private final ICustomerRepository repository = mock(ICustomerRepository.class);
    private final CustomerMapper mapper = mock(CustomerMapper.class);
    private final GetCustomerService service = new GetCustomerService(repository);

    @Test
    @DisplayName("Should throw if customer not found")
    void shouldThrowIfCustomerNotFound() {
        ApiException exception = Assertions.assertThrows(ApiException.class,
                () -> service.execute(anyLong()));

        verify(repository, times(1)).findById(anyLong());
        assertEquals("Customer not found", exception.getMessage());
    }

    @Test
    @DisplayName("Should return customer with success")
    void shouldReturnCustomerWithSuccess() {
        CustomerEntity entity = new CustomerEntity();
        entity.setId(1L);
        entity.setName("name");
        entity.setDocument("12345678912");
        entity.setPhoneNumber("5521987654321");
        entity.setEmail("email@email.com");
        entity.setBirthDate(LocalDate.of(2000, Month.JANUARY,1));
        Optional<CustomerEntity> optionalEntity = Optional.of(entity);

        doReturn(optionalEntity).when(repository).findById(anyLong());
        CustomerEntity customer = service.execute(1L);

        assertEquals(entity.getId(), customer.getId());
        assertEquals(entity.getName(), customer.getName());
        assertEquals(entity.getDocument(), customer.getDocument());
        assertEquals(entity.getEmail(), customer.getEmail());
        assertEquals(entity.getPhoneNumber(), customer.getPhoneNumber());
        assertEquals(entity.getBirthDate(), customer.getBirthDate());
        verify(repository, times(1)).findById(anyLong());
    }

}
