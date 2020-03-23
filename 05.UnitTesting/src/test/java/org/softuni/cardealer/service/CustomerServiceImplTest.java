package org.softuni.cardealer.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.softuni.cardealer.domain.entities.Customer;
import org.softuni.cardealer.domain.models.service.CustomerServiceModel;
import org.softuni.cardealer.repository.CustomerRepository;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class CustomerServiceImplTest {

    @Mock
    CustomerRepository repository;

    @Mock
    ModelMapper modelMapper;

    @InjectMocks
    CustomerServiceImpl service;

    CustomerServiceModel model;
    Customer customer;

    @Before
    public void setUp() {
        this.model = mock(CustomerServiceModel.class);
        this.customer = mock(Customer.class);

        when(this.modelMapper.map(eq(null), any()))
                .thenThrow(new IllegalArgumentException());
    }

    @Test
    public void saveCustomer_withValidInput_shouldReturnCorrect() {
        when(this.modelMapper.map(this.model, Customer.class)).thenReturn(this.customer);
        when(this.repository.saveAndFlush(this.customer)).thenReturn(this.customer);
        when(this.modelMapper.map(this.customer, CustomerServiceModel.class)).thenReturn(this.model);

        CustomerServiceModel actualModel = this.service.saveCustomer(this.model);

        verify(this.modelMapper).map(this.model, Customer.class);
        verify(this.repository).saveAndFlush(this.customer);
        verify(this.modelMapper).map(this.model, Customer.class);
        assertEquals(this.model, actualModel);
    }

    @Test(expected = IllegalArgumentException.class)
    public void saveCustomer_withNullInput_shouldThrow() {
        this.service.saveCustomer(null);
    }

    @Test
    public void editCustomer_withValidInput_shouldReturnCorrect() {
        LocalDate bDay = LocalDate.now();
        when(this.model.getId()).thenReturn("id");
        when(this.model.getName()).thenReturn("name");
        when(this.model.getBirthDate()).thenReturn(bDay);
        when(this.repository.findById("id")).thenReturn(Optional.of(this.customer));
        when(this.repository.saveAndFlush(this.customer)).thenReturn(this.customer);
        when(this.modelMapper.map(this.customer, CustomerServiceModel.class)).thenReturn(this.model);

        CustomerServiceModel actualModel = this.service.editCustomer(this.model);

        verify(this.repository).findById("id");
        verify(this.repository).saveAndFlush(this.customer);
        verify(this.modelMapper).map(this.customer, CustomerServiceModel.class);
        assertEquals(this.model, actualModel);
    }

    @Test(expected = NullPointerException.class)
    public void editCustomer_withNonExistingId_shouldThrow() {
        when(this.model.getId()).thenReturn("id");
        when(this.repository.findById("id")).thenReturn(Optional.empty());

        this.service.editCustomer(this.model);
    }

    @Test(expected = NullPointerException.class)
    public void editCustomer_withNullEntity_shouldThrow() {
        this.service.editCustomer(null);
    }

    @Test
    public void deleteCustomer_withValidInput_shouldReturnCorrect() {
        when(this.repository.findById("id")).thenReturn(Optional.of(this.customer));
        when(this.modelMapper.map(customer, CustomerServiceModel.class)).thenReturn(this.model);

        CustomerServiceModel actualModel = this.service.deleteCustomer("id");

        verify(this.repository).findById("id");
        verify(this.repository).delete(this.customer);
        verify(this.modelMapper).map(this.customer, CustomerServiceModel.class);
        assertEquals(this.model, actualModel);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deleteCustomer_withNonExistingId_shouldThrow() {
        when(this.repository.findById("id")).thenReturn(Optional.empty());

        this.service.deleteCustomer("id");
    }

    @Test(expected = IllegalArgumentException.class)
    public void deleteCustomer_withNullId_shouldThrow() {
        this.service.deleteCustomer(null);
    }

    @Test
    public void findCustomerById_withValidInput_shouldReturnCorrect() {
        when(this.repository.findById("id")).thenReturn(Optional.of(this.customer));
        when(this.modelMapper.map(customer, CustomerServiceModel.class)).thenReturn(this.model);

        CustomerServiceModel actualModel = this.service.findCustomerById("id");

        verify(this.repository).findById("id");
        verify(this.modelMapper).map(customer, CustomerServiceModel.class);
        assertEquals(this.model, actualModel);
    }

    @Test(expected = IllegalArgumentException.class)
    public void findCustomerById_withNonExistingId_shouldThrow() {
        when(this.repository.findById("id")).thenReturn(Optional.empty());

        this.service.findCustomerById("id");
    }

    @Test(expected = IllegalArgumentException.class)
    public void findCustomerById_withNullId_shouldThrow() {
        this.service.findCustomerById(null);
    }
}