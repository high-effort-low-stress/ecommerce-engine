package com.hels.ecommerceengine.modules.customer.service;

import com.hels.ecommerceengine.exceptions.ApiException;
import com.hels.ecommerceengine.modules.customer.dto.CreateCustomerDto;
import com.hels.ecommerceengine.modules.customer.entity.CustomerEntity;
import com.hels.ecommerceengine.modules.customer.mapper.CustomerMapper;
import com.hels.ecommerceengine.modules.customer.repository.ICustomerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.*;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;


public class CreateCostumerServiceTest {
    private final ICustomerRepository repository = mock(ICustomerRepository.class);
    private final CustomerMapper mapper = mock(CustomerMapper.class);
    private final Clock clock =
            Clock.fixed(
                    LocalDate.of(2024, Month.FEBRUARY, 1).atStartOfDay().toInstant(ZoneOffset.UTC),
                    ZoneId.systemDefault()
            );
    private final CreateCustomerService service = new CreateCustomerService(repository, mapper,clock);


    @Test
    @DisplayName("Should throw if age is under 18 years old")
    void shouldThrowIfAgeIsUnder18YearsOld() {
        CreateCustomerDto.Request customerRequest = sampleCreateCustomerAccountDtoRequest();
        customerRequest.setBirthDate(LocalDate.of(2020,Month.FEBRUARY,1));

        ApiException exception = Assertions.assertThrows(ApiException.class,
                () -> service.execute(customerRequest));

        verify(repository, times(0)).save(any());
        assertEquals("Customer must be 18+ years old", exception.getMessage());
    }
    @Test
    @DisplayName("Should throw if birth date is null")
    void shouldThrowIfBirthDateIsNull() {
        CreateCustomerDto.Request customerRequest = sampleCreateCustomerAccountDtoRequest();
        customerRequest.setBirthDate(null);

        RuntimeException exception = Assertions.assertThrows(RuntimeException.class,
                () -> service.execute(customerRequest));

        verify(repository, never()).save(any());
        assertEquals("Birth date can't be null", exception.getMessage());
    }

    @Test
    @DisplayName("Should throw if document, email or phone number is duplicated")
    void shouldThrowIfDocumentEmailOrPhoneNumberIsDuplicated() {
        CreateCustomerDto.Request customerRequest = sampleCreateCustomerAccountDtoRequest();
        Optional<CustomerEntity> optionalCustomerEntity = Optional.of(new CustomerEntity());

        doReturn(optionalCustomerEntity).when(repository).
                findByDocumentAndEmailAndPhoneNumber(anyString(), anyString(), anyString());
        ApiException exception = Assertions.assertThrows(ApiException.class,
                () -> service.execute(customerRequest));

        verify(repository, never()).save(any());
        assertEquals("Customer already registered", exception.getMessage());
    }

    @Test
    @DisplayName("Should save customer if no errors were found")
    void shouldCreateCustomerAccountIfNoErrorsWereFound() {
        CustomerEntity entity = new CustomerEntity();
        entity.setId(1L);
        CreateCustomerDto.Request request = sampleCreateCustomerAccountDtoRequest();

        doReturn(entity).when(repository).save(any());
        CustomerEntity customer = service.execute(request);

        verify(repository, times(1)).save(any());
        assertNotNull(request);
        assertEquals(1L ,customer.getId());
    }

    private CreateCustomerDto.Request sampleCreateCustomerAccountDtoRequest() {
        return new CreateCustomerDto.Request(
                "name",
                "66854909007",
                "email@email.com",
                "password",
                "5521987654321",
                LocalDate.of(2000,Month.FEBRUARY,1)
        );
    }
}
