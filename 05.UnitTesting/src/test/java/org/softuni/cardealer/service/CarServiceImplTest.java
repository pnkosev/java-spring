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

import java.util.Optional;

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

    @Test
    public void editCar_withValidInput_shouldReturnCorrect() {
        CarServiceModel expectedModel = mock(CarServiceModel.class);
        Car car = mock(Car.class);

        when(expectedModel.getId()).thenReturn("id");
        when(expectedModel.getMake()).thenReturn("make");
        when(expectedModel.getModel()).thenReturn("model");
        when(expectedModel.getTravelledDistance()).thenReturn(250000L);
        when(this.repository.findById("id")).thenReturn(Optional.of(car));
        when(this.repository.saveAndFlush(car)).thenReturn(car);
        when(this.modelMapper.map(car, CarServiceModel.class)).thenReturn(expectedModel);

        CarServiceModel actualModel = this.service.editCar(expectedModel);

        verify(this.repository).findById("id");
        verify(this.repository).saveAndFlush(car);
        verify(this.modelMapper).map(car, CarServiceModel.class);
        assertEquals(expectedModel, actualModel);
    }

    @Test(expected = NullPointerException.class)
    public void editCar_withNonExistingId_shouldThrow() {
        CarServiceModel model = mock(CarServiceModel.class);

        when(model.getId()).thenReturn("id");
        when(this.repository.findById("id")).thenReturn(Optional.empty());

        this.service.editCar(model);
    }

    @Test(expected = NullPointerException.class)
    public void editCar_withNullInput_shouldThrow() {
        this.service.editCar(null);
    }

    @Test
    public void deleteCar_withValidInput_shouldReturnCorrect() {
        Car car = mock(Car.class);
        CarServiceModel expectedModel = mock(CarServiceModel.class);

        when(this.repository.findById("id")).thenReturn(Optional.of(car));
        when(this.modelMapper.map(car, CarServiceModel.class)).thenReturn(expectedModel);

        CarServiceModel actualModel = this.service.deleteCar("id");

        verify(this.repository).findById("id");
        verify(this.modelMapper).map(car, CarServiceModel.class);
        assertEquals(expectedModel, actualModel);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deleteCar_withNonExistingEntity_shouldThrow() {
        when(this.repository.findById("id")).thenReturn(Optional.empty());

        this.service.deleteCar("id");
    }

    @Test(expected = IllegalArgumentException.class)
    public void deleteCar_withNullId_shouldThrow() {
        this.service.deleteCar(null);
    }

    @Test
    public void findCarById_withValidInput_shouldReturnCorrect() {
        Car car = mock(Car.class);
        CarServiceModel expectedModel = mock(CarServiceModel.class);

        when(this.repository.findById("id")).thenReturn(Optional.of(car));
        when(this.modelMapper.map(car, CarServiceModel.class)).thenReturn(expectedModel);

        CarServiceModel actualModel = this.service.findCarById("id");

        verify(this.repository).findById("id");
        verify(this.modelMapper).map(car, CarServiceModel.class);
        assertEquals(expectedModel, actualModel);
    }

    @Test(expected = IllegalArgumentException.class)
    public void findCarById_withNonExistingId_shouldThrow() {
        when(this.repository.findById("id")).thenReturn(Optional.empty());
        this.service.findCarById("id");
    }

    @Test(expected = IllegalArgumentException.class)
    public void findCarById_withNullId_shouldThrow() {
        this.service.findCarById(null);
    }
}