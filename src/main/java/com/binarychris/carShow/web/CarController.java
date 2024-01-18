package com.binarychris.carShow.web;

import com.binarychris.carShow.entity.Car;
import com.binarychris.carShow.service.CarService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/car")
public class CarController {
    // Json - like Map(key, value), list
    // JavaScript Object Notation
    private final CarService carService;
// this is called Constructor Injection, the best injection to use because final is safe
    public CarController(CarService carService) {
        this.carService = carService;
    }
    @GetMapping("/cars")
    public ResponseEntity<List<Car>> getCars(){
        return new ResponseEntity<>(carService.getCars(), HttpStatus.OK); // OK means 200
    }
    @GetMapping("/{id}")
    public ResponseEntity<Car> getCarById(@PathVariable Long id){
        return new ResponseEntity<>(carService.getCarById(id), HttpStatus.OK);
    }
    // HTTP method indicate the action of an API client
    // HttpStatus to show user it is good??
    // 100 Information, 200 Successful (201 Created), 300 Redirection, 400 Client error (404 Not found), 500 Server error
    @PostMapping("/add")
    public ResponseEntity<Car> addCar(@RequestBody Car car){
        return new ResponseEntity<>(carService.addCar(car), HttpStatus.CREATED);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Car> deleteCarById(@PathVariable Long id){
        carService.deleteCarById(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Car> updateCarById(@PathVariable Long id, @RequestBody Car car){
        return new ResponseEntity<>(carService.updateCarById(id, car), HttpStatus.ACCEPTED);
    }
}
