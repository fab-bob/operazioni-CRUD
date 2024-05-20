package com.example.operazioniCRUD;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CarController {

    @Autowired
    CarRepository carRepository;

    @PostMapping("/cars")
    public Car create(@RequestBody Car car) {
        return carRepository.saveAndFlush(car);
    }

    @GetMapping("/cars")
    public List<Car> getAll() {
        return carRepository.findAll();
    }

    @GetMapping("/cars/{id}")
    public Car getOne(@PathVariable int id) {

        return carRepository.findById(id).orElse(new Car());
    }

    @PatchMapping("/cars/{id}")
    public Car updateType(@PathVariable int id, @RequestParam String type) {
        if (!carRepository.existsById(id)) {
            return new Car();
        }
        carRepository.getReferenceById(id).setType(type);
        return carRepository.findById(id).get();
    }

    @DeleteMapping("/cars/{id}")
    public ResponseEntity<Car> delete(@PathVariable int id) {
        if (!carRepository.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        carRepository.delete(carRepository.getReferenceById(id));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/cars")
    public void deleteAll() {
        carRepository.deleteAll();
    }

}
