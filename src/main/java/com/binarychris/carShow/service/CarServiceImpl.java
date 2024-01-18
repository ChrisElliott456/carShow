package com.binarychris.carShow.service;

import com.binarychris.carShow.entity.Car;
import com.binarychris.carShow.exception.ResourceNotFoundException;
import com.binarychris.carShow.repository.CarRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarServiceImpl implements CarService{
// @Autowired can't work here because it's final, must use constructor
    private final CarRepository carRepository;
// can type @Autowired here, but it's optional
    public CarServiceImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public List<Car> getCars() {
        return (List<Car>) carRepository.findAll();//casted so could have list
    }

    @Override
    public Car getCarById(Long id) {
//        Optional<Car> optionalCar = carRepository.findById(id);
//        if (optionalCar.isPresent()){
//            return optionalCar.get();
//        }else {
//            throw new ResourceNotFoundException("Car with id " + id + " not found.");
//        }
        // showing off without Optional
        return carRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Car with id " + id + " not found."));
    }

    @Override
    public Car addCar(Car car) {
        return carRepository.save(car);
    } //  will add car to database

    @Override
    public void deleteCarById(Long id) {
        carRepository.deleteById(id);
    }

    @Override
    public Car updateCarById(Long id, Car car) {
        Car existingCar = getCarById(id);
        existingCar.setMake(car.getMake());
        existingCar.setModel(car.getModel());
        existingCar.setColor(car.getColor());
        existingCar.setRegisterNumber(car.getRegisterNumber());
        existingCar.setYear(car.getYear());
        existingCar.setPrice(car.getPrice());
        carRepository.save(existingCar);
        return existingCar;
    }
}
// Optional : is a container object used to represent a value that may or may not be present.
