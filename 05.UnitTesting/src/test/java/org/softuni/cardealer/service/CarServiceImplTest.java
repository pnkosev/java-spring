package org.softuni.cardealer.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.softuni.cardealer.domain.entities.Car;
import org.softuni.cardealer.domain.models.service.CarServiceModel;
import org.softuni.cardealer.repository.CarRepository;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class CarServiceImplTest {

    @Mock
    CarRepository repository;

    @Mock
    ModelMapper modelMapper;

    @InjectMocks
    CarServiceImpl service;

    @Before
    public void setUp() {
        when(this.modelMapper.map(eq(null), any())).thenThrow(new IllegalArgumentException());
    }

    @Test
    public void saveCar_withValidInput_shouldReturnCorrect() {
        Car car = mock(Car.class);
        CarServiceModel expectedModel = mock(CarServiceModel.class);

        when(this.modelMapper.map(expectedModel, Car.class)).thenReturn(car);
        when(this.repository.saveAndFlush(car)).thenReturn(car);
        when(this.modelMapper.map(car, CarServiceModel.class)).thenReturn(expectedModel);

        CarServiceModel actualModel = this.service.saveCar(expectedModel);

        verify(this.modelMapper).map(car, CarServiceModel.class);
        verify(this.repository).saveAndFlush(car);
        verify(this.modelMapper).map(expectedModel, Car.class);
        assertEquals(expectedModel, actualModel);
    }

    @Test(expected = IllegalArgumentException.class)
    public void saveCar_withNullInput_shouldThrow() {
        this.service.saveCar(null);
    }
}