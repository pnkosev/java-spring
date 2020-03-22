package org.softuni.cardealer.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.softuni.cardealer.domain.entities.Supplier;
import org.softuni.cardealer.domain.models.service.SupplierServiceModel;
import org.softuni.cardealer.repository.SupplierRepository;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class SupplierServiceImplTest {

    @Mock
    SupplierRepository repository;

    @Mock
    ModelMapper modelMapper;

    @InjectMocks
    SupplierServiceImpl service;

    @Before
    public void initTests() {
        when(modelMapper.map(eq(null), any()))
                .thenThrow(new IllegalArgumentException());
    }

    @Test
    public void saveSupplier_withCorrectInput_shouldReturnCorrect() {
        SupplierServiceModel model = mock(SupplierServiceModel.class);
        Supplier supplier = mock(Supplier.class);

        when(this.modelMapper.map(model, Supplier.class)).thenReturn(supplier);
        when(this.modelMapper.map(supplier, SupplierServiceModel.class)).thenReturn(model);

        SupplierServiceModel actual = this.service.saveSupplier(model);

        verify(this.repository).saveAndFlush(supplier);

        assertEquals(model, actual);

//        SupplierServiceModel model = mock(SupplierServiceModel.class);
//
//        this.service.saveSupplier(model);
//
//        ArgumentCaptor<Supplier> arg = ArgumentCaptor.forClass(Supplier.class);
//
//        verify(this.repository).saveAndFlush(arg.capture());
//
//        Supplier supplier = arg.getValue();
//
//        assertNotNull(supplier);
    }

    @Test(expected = IllegalArgumentException.class)
    public void saveSupplier_withNullInput_shouldThrow() {
        this.service.saveSupplier(null);
    }

    @Test
    public void editSupplier_withCorrectInput_shouldReturnCorrect() {
        Supplier supplier = mock(Supplier.class);
        SupplierServiceModel model = mock(SupplierServiceModel.class);
        when(model.getId()).thenReturn("id");
        when(model.getName()).thenReturn("name");
        when(model.isImporter()).thenReturn(true);
        when(this.repository.findById(eq("id"))).thenReturn(Optional.of(supplier));
        when(this.repository.saveAndFlush(supplier)).thenReturn(supplier);
        when(this.modelMapper.map(supplier, SupplierServiceModel.class)).thenReturn(model);

        SupplierServiceModel actualResult = this.service.editSupplier(model);

        verify(this.repository).findById("id");
        verify(supplier).setName("name");
        verify(supplier).setImporter(true);
        verify(this.repository).saveAndFlush(supplier);

        assertEquals(model, actualResult);
    }

    @Test(expected = NullPointerException.class)
    public void editSupplier_withNullInput_shouldThrow() {
        this.service.editSupplier(null);
    }

    @Test(expected = NullPointerException.class)
    public void editSupplier_withNonExistingEntity_shouldThrow() {
        SupplierServiceModel model = mock(SupplierServiceModel.class);
        when(model.getId()).thenReturn("id");
        when(this.repository.findById("id")).thenReturn(Optional.empty());

        this.service.editSupplier(model);
    }

    @Test
    public void deleteSupplier_withExistingEntity_shouldReturnCorrect() {
        SupplierServiceModel model = mock(SupplierServiceModel.class);
        Supplier supplier = mock(Supplier.class);

        when(this.repository.findById("id")).thenReturn(Optional.of(supplier));
        when(this.modelMapper.map(supplier, SupplierServiceModel.class)).thenReturn(model);

        SupplierServiceModel actual = this.service.deleteSupplier("id");

        verify(this.repository).findById("id");
        verify(this.repository).delete(supplier);
        verify(this.modelMapper).map(supplier, SupplierServiceModel.class);
        assertEquals(model, actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deleteSupplier_withNonExistingId_shouldThrow() {
        when(this.repository.findById("id")).thenReturn(Optional.empty());
        this.service.deleteSupplier("id");
    }

    @Test(expected = IllegalArgumentException.class)
    public void deleteSupplier_withNullId_shouldThrow() {
        this.service.deleteSupplier(null);
    }

    @Test
    public void findSupplierById_withValidId_shouldReturnCorrect() {
        Supplier supplier = mock(Supplier.class);
        SupplierServiceModel expected = mock(SupplierServiceModel.class);

        when(this.modelMapper.map(supplier, SupplierServiceModel.class)).thenReturn(expected);
        when(this.repository.findById("id")).thenReturn(Optional.of(supplier));

        SupplierServiceModel actual = this.service.findSupplierById("id");

        verify(this.repository).findById("id");
        verify(this.modelMapper).map(supplier, SupplierServiceModel.class);
        assertEquals(expected, actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void findSupplierById_withNullId_shouldThrow() {
        this.service.findSupplierById(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void findSupplierById_withNonExistingId_shouldThrow() {
        when(this.repository.findById("id")).thenReturn(Optional.empty());

        this.service.findSupplierById("id");
    }
}