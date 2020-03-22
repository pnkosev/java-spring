package org.softuni.cardealer.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.softuni.cardealer.domain.entities.Part;
import org.softuni.cardealer.domain.models.service.PartServiceModel;
import org.softuni.cardealer.repository.PartRepository;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class PartServiceImplTest {

    @Mock
    PartRepository repository;

    @Mock
    ModelMapper modelMapper;

    @InjectMocks
    PartServiceImpl service;

    @Before
    public void setUp() {
        when(this.modelMapper.map(eq(null), any()))
                .thenThrow(new IllegalArgumentException());
    }

    @Test
    public void savePart_withValidInput_shouldReturnCorrect() {
        PartServiceModel expectedModel = mock(PartServiceModel.class);
        Part part = mock(Part.class);

        when(this.modelMapper.map(expectedModel, Part.class)).thenReturn(part);
        when(this.modelMapper.map(part, PartServiceModel.class)).thenReturn(expectedModel);

        PartServiceModel actualModel = this.service.savePart(expectedModel);

        verify(this.modelMapper).map(expectedModel, Part.class);
        verify(this.repository).saveAndFlush(part);
        verify(this.modelMapper).map(part, PartServiceModel.class);

        assertEquals(expectedModel, actualModel);
    }

    @Test(expected = IllegalArgumentException.class)
    public void savePart_withNullInput_shouldThrow() {
        this.service.savePart(null);
    }

    @Test
    public void editPart_withValidInpit_shouldReturnCorrect() {
        Part part = mock(Part.class);
        PartServiceModel expectedModel = mock(PartServiceModel.class);

        when(expectedModel.getId()).thenReturn("id");
        when(expectedModel.getName()).thenReturn("name");
        when(expectedModel.getPrice()).thenReturn(new BigDecimal("2"));
        when(this.repository.findById("id")).thenReturn(Optional.of(part));
        when(this.repository.saveAndFlush(part)).thenReturn(part);
        when(this.modelMapper.map(part, PartServiceModel.class)).thenReturn(expectedModel);

        PartServiceModel actualModel = this.service.editPart(expectedModel);

        verify(this.repository).findById("id");
        verify(part).setName("name");
        verify(part).setPrice(new BigDecimal("2"));
        verify(repository).saveAndFlush(part);
        verify(this.modelMapper).map(part, PartServiceModel.class);
        
        assertEquals(expectedModel, actualModel);
    }

    @Test(expected = NullPointerException.class)
    public void editPart_withNonExistingEntity_shouldThrow() {
        PartServiceModel model = mock(PartServiceModel.class);

        when(model.getId()).thenReturn("id");
        when(this.repository.findById("id")).thenReturn(Optional.empty());

        this.service.editPart(model);
    }

    @Test(expected = NullPointerException.class)
    public void editPart_withNullInput_shouldThrow() {
        this.service.editPart(null);
    }

    @Test
    public void deletePart_withCorrectInput_shouldReturnCorrect() {
        PartServiceModel expectedModel = mock(PartServiceModel.class);
        Part part = mock(Part.class);

        when(this.repository.findById("id")).thenReturn(Optional.of(part));
        when(this.modelMapper.map(part, PartServiceModel.class)).thenReturn(expectedModel);

        PartServiceModel actualModel = this.service.deletePart("id");

        verify(this.repository).findById("id");
        verify(this.repository).delete(part);
        verify(this.modelMapper).map(part, PartServiceModel.class);
        assertEquals(expectedModel, actualModel);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deletePart_withNullId_shouldThrow() {
        when(this.repository.findById(null)).thenThrow(new IllegalArgumentException());

        this.service.deletePart(null);
    }

    @Test(expected = NoSuchElementException.class)
    public void deletePart_withNonExistingId_shouldThrow() {
        when(this.repository.findById("id")).thenReturn(Optional.empty());

        this.service.deletePart("id");
    }

    @Test
    public void findPartById_withExistingId_shouldReturnCorrect() {
        Part part = mock(Part.class);
        PartServiceModel expectedModel = mock(PartServiceModel.class);

        when(this.repository.findById("id")).thenReturn(Optional.of(part));
        when(this.modelMapper.map(part, PartServiceModel.class)).thenReturn(expectedModel);

        PartServiceModel actualModel = this.service.findPartById("id");

        verify(this.repository).findById("id");
        verify(this.modelMapper).map(part, PartServiceModel.class);
        assertEquals(expectedModel, actualModel);
    }

    @Test(expected = IllegalArgumentException.class)
    public void findPartById_withNullId_shouldThrow() {
        this.service.findPartById(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void findPartById_withNonExistingEntity_shouldThrow() {
        when(this.repository.findById("id")).thenReturn(Optional.empty());

        this.service.findPartById("id");
    }
}
