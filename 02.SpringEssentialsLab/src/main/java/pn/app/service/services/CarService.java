package pn.app.service.services;

import pn.app.service.models.CarServiceModel;

import java.util.List;

public interface CarService {
    void save(CarServiceModel carServiceModel);

    List<CarServiceModel> listAll();
}
