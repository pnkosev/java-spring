package pn.app.service.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import pn.app.data.models.Car;
import pn.app.data.repository.CarRepository;
import pn.app.service.models.CarServiceModel;
import pn.app.service.services.CarService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;

    private final ModelMapper modelMapper;

    public CarServiceImpl(CarRepository carRepository, ModelMapper modelMapper) {
        this.carRepository = carRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void save(CarServiceModel carServiceModel) {
        this.carRepository.save(this.modelMapper.map(carServiceModel, Car.class));
    }

    @Override
    public List<CarServiceModel> listAll() {
        return this.carRepository.findAll()
                .stream()
                .map(c -> this.modelMapper.map(c, CarServiceModel.class))
                .collect(Collectors.toList());
    }
}
