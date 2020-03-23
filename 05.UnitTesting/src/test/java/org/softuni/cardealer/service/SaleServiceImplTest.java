package org.softuni.cardealer.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.softuni.cardealer.domain.entities.CarSale;
import org.softuni.cardealer.domain.entities.PartSale;
import org.softuni.cardealer.domain.models.service.CarSaleServiceModel;
import org.softuni.cardealer.domain.models.service.PartSaleServiceModel;
import org.softuni.cardealer.repository.CarSaleRepository;
import org.softuni.cardealer.repository.PartSaleRepository;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class SaleServiceImplTest {

    @Mock
    CarSaleRepository carRepository;

    @Mock
    PartSaleRepository partRepository;

    @Mock
    ModelMapper modelMapper;

    @InjectMocks
    SaleServiceImpl service;

    @Before
    public void setUp() {
        when(this.modelMapper.map(eq(null), any())).thenThrow(new IllegalArgumentException());
    }

    @Test
    public void saleCar_withValidInput_shouldReturnCorrect() {
        CarSaleServiceModel carModel = mock(CarSaleServiceModel.class);
        CarSale carSale = mock(CarSale.class);

        when(this.modelMapper.map(carModel, CarSale.class)).thenReturn(carSale);
        when(this.carRepository.saveAndFlush(carSale)).thenReturn(carSale);
        when(this.modelMapper.map(carSale, CarSaleServiceModel.class)).thenReturn(carModel);

        CarSaleServiceModel actualCarModel = this.service.saleCar(carModel);

        verify(this.modelMapper).map(carModel, CarSale.class);
        verify(this.carRepository).saveAndFlush(carSale);
        verify(this.modelMapper).map(carSale, CarSaleServiceModel.class);
        assertEquals(carModel, actualCarModel);
    }

    @Test(expected = IllegalArgumentException.class)
    public void saleCar_withNullModel_shouldThrow() {
        this.service.saleCar(null);
    }

    @Test
    public void salePart_withValidInput_shouldReturnCorrect() {
        PartSaleServiceModel partModel = mock(PartSaleServiceModel.class);
        PartSale partSale = mock(PartSale.class);

        when(this.modelMapper.map(partModel, PartSale.class)).thenReturn(partSale);
        when(this.partRepository.saveAndFlush(partSale)).thenReturn(partSale);
        when(this.modelMapper.map(partSale, PartSaleServiceModel.class)).thenReturn(partModel);

        PartSaleServiceModel actualCarModel = this.service.salePart(partModel);

        verify(this.modelMapper).map(partModel, PartSale.class);
        verify(this.partRepository).saveAndFlush(partSale);
        verify(this.modelMapper).map(partSale, PartSaleServiceModel.class);
        assertEquals(partModel, actualCarModel);
    }

    @Test(expected = IllegalArgumentException.class)
    public void salePart_withNullModel_shouldThrow() {
        this.service.salePart(null);
    }
}
