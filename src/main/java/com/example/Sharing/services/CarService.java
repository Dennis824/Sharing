package com.example.Sharing.services;

import com.example.Sharing.model.Car;
import com.example.Sharing.repositories.CarRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(readOnly = true)
public class CarService {
    private final CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public Iterable<Car> findAll() {
        return carRepository.findAll();
    }

    public Car findById(long id) {
        return carRepository.findById(id);
    }

    public void deleteById(long id){ carRepository.deleteById(id);}

    @Transactional
    public void delete(Car car) throws Exception {
        carRepository.delete(car);
    }

    @Transactional
    public void save(Car car) throws Exception{
        if (carRepository.findByNumber(car.getNumber())==null|| carRepository.findByNumber(car.getNumber()).getId()== car.getId()) {
            carRepository.save(car);
            return;
        }
        throw new Exception("Another car already has this number");
    }
}
