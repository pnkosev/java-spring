package org.softuni.cardealer;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.softuni.cardealer.domain.entities.Supplier;
import org.softuni.cardealer.domain.models.service.SupplierServiceModel;
import org.softuni.cardealer.repository.SupplierRepository;
import org.softuni.cardealer.service.SupplierService;
import org.softuni.cardealer.service.SupplierServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class SupplierServiceImplTest {

    @Autowired
    SupplierRepository repository;

    ModelMapper modelMapper;

    SupplierService service;

    SupplierServiceModel model;

    @Before
    public void setup() {
        this.modelMapper = new ModelMapper();
        this.service = new SupplierServiceImpl(this.repository, this.modelMapper);
        this.model = new SupplierServiceModel();
    }

    @Test
    public void saveSupplier_withValidInput_shouldReturnCorrect() {
        this.fillModel();
        SupplierServiceModel actual = this.service.saveSupplier(this.model);
        SupplierServiceModel expected = this.modelMapper.map(this.repository.findAll().get(0), SupplierServiceModel.class);

        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getName(), actual.getName());
        assertEquals(expected.isImporter(), actual.isImporter());
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void saveSupplier_withInvalidInput_shouldThrow() {
        this.service.saveSupplier(this.model);
    }

    @Test(expected = IllegalArgumentException.class)
    public void saveSupplier_withNullInput_shouldThrow() {
        this.service.saveSupplier(null);
    }

    @Test
    public void editSupplier_withValidInput_shouldReturnCorrect() {
        this.fillModel();

        Supplier supplier = this.repository.saveAndFlush(this.modelMapper.map(this.model, Supplier.class));

        this.model.setId(supplier.getId());
        this.model.setName("newName");

        SupplierServiceModel actual = this.service.editSupplier(this.model);
        SupplierServiceModel expected = this.modelMapper.map(this.repository.findAll().get(0), SupplierServiceModel.class);

        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getName(), actual.getName());
        assertEquals(expected.isImporter(), actual.isImporter());
    }

    @Test(expected = NullPointerException.class)
    public void editSupplier_withNonExistingId_shouldThrow() {
        this.fillModel();
        this.model.setId("1");

        this.service.editSupplier(this.model);
    }

    @Test(expected = InvalidDataAccessApiUsageException.class)
    public void editSupplier_withNullId_shouldThrow() {
        this.fillModel();
        this.service.editSupplier(this.model);
    }

    @Test
    public void deleteSupplier_withValidInput_shouldReturnCorrect() {
        this.fillModel();

        Supplier supplier = this.repository.saveAndFlush(this.modelMapper.map(this.model, Supplier.class));

        this.service.deleteSupplier(supplier.getId());

        int size = this.repository.findAll().size();

        assertEquals(0, size);
    }

    @Test(expected = InvalidDataAccessApiUsageException.class)
    public void deleteSupplier_withNonExistingId_shouldThrow() {
        this.service.deleteSupplier("id");
    }

    @Test
    public void findSupplierById_withValidInput_shouldReturnCorrect() {
        this.fillModel();

        Supplier supplier = this.repository.saveAndFlush(this.modelMapper.map(this.model, Supplier.class));

        SupplierServiceModel actual = this.service.findSupplierById(supplier.getId());
        SupplierServiceModel expected = this.modelMapper.map(this.repository.findAll().get(0), SupplierServiceModel.class);

        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getName(), actual.getName());
        assertEquals(expected.isImporter(), actual.isImporter());
    }

    @Test(expected = IllegalArgumentException.class)
    public void findSupplierById_withNonExistingId_shouldThrow() {
        this.service.findSupplierById("1");
    }

    private void fillModel() {
        this.model.setName("name");
        this.model.setImporter(true);
    }
}
