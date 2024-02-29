package com.hels.ecommerceengine.modules.customer.service;

import com.hels.ecommerceengine.exceptions.ApiException;
import com.hels.ecommerceengine.modules.customer.dto.CreateCustomerDto;
import com.hels.ecommerceengine.modules.customer.entity.CustomerEntity;
import com.hels.ecommerceengine.modules.customer.mapper.CustomerMapper;
import com.hels.ecommerceengine.modules.customer.repository.ICustomerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Optional;

import static org.mockito.Mockito.*;


public class CreateCostumerAccountServiceTest {
    private final ICustomerRepository repository = mock(ICustomerRepository.class);
    private final CustomerMapper mapper = mock(CustomerMapper.class);
    private final CreateCustomerService service = new CreateCustomerService(repository, mapper);


    @Test
    @DisplayName("Should throw if age is under 18 years old")
    void shouldThrowIfAgeIsUnder18YearsOld() {
        CreateCustomerDto.Request customerRequest = sampleCreateCustomerAccountDtoRequest();
        customerRequest.setBirthDate(LocalDate.of(2020,1,1));

        ApiException exception = Assertions.assertThrows(ApiException.class, () -> service.execute(customerRequest));

        Assertions.assertEquals("Customer must be 18+ years old", exception.getMessage());
    }
    @Test
    @DisplayName("Should throw if birth date is null")
    void shouldThrowIfBirthDateIsNull() {
        CreateCustomerDto.Request customerRequest = sampleCreateCustomerAccountDtoRequest();
        customerRequest.setBirthDate(null);

        RuntimeException exception = Assertions.assertThrows(RuntimeException.class,
                () -> service.execute(customerRequest));

        Assertions.assertEquals("Birth date can't be null", exception.getMessage());
    }

    @Test
    @DisplayName("Should throw if document, email or phone number is duplicated")
    void shouldThrowIfDocumentEmailOrPhoneNumberIsDuplicated() {
        CreateCustomerDto.Request customerRequest= sampleCreateCustomerAccountDtoRequest();

        var optionalCustomer = Optional.of(new CustomerEntity());

        doReturn(optionalCustomer).when(repository).findByDocumentAndEmailAndPhoneNumber(anyString(), anyString(), anyString());

        ApiException exception = Assertions.assertThrows(ApiException.class, () -> service.execute(customerRequest));

        Assertions.assertEquals("Customer already registered", exception.getMessage());
    }

    @Test
    @DisplayName("Should save customer if no errors were found")
    void shouldCreateCustomerAccountIfNoErrorsWereFound() {

        doReturn(new CustomerEntity()).when(repository).save(any());

        service.execute(sampleCreateCustomerAccountDtoRequest());

        Assertions.assertNotNull(sampleCreateCustomerAccountDtoRequest());
        verify(repository, times(1)).save(any());
    }

    private CreateCustomerDto.Request sampleCreateCustomerAccountDtoRequest() {
        return new CreateCustomerDto.Request(
                "name",
                "66854909007",
                "email@email.com",
                "password",
                "5521987654321",
                LocalDate.of(2000,1,1)
        );
    }


}
