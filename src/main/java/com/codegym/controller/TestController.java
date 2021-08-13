package com.codegym.controller;

import com.codegym.model.City;
import com.codegym.service.ICityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class TestController {
    @Autowired
    private ICityService cityService;
    @GetMapping("/cities")
    public ResponseEntity<Iterable<City>>findAll(){
        Iterable<City>cities=cityService.findAll();
        return new ResponseEntity<>(cities, HttpStatus.OK);
    }
    @PostMapping("/cities")
    public ResponseEntity<Optional<City>>create(@RequestBody City city){
        cityService.save(city);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Optional<City>>delete(@PathVariable Long id){
        Optional<City>city=cityService.findById(id);
        if (!city.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        cityService.remove(id);
        return new ResponseEntity<>(city,HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<City>edit(@PathVariable Long id,@RequestBody City city){
        Optional<City>currentCity=cityService.findById(id);
        if (!currentCity.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        city.setId(id);
        cityService.save(city);
        return new ResponseEntity<>(city,HttpStatus.OK);
    }
    @GetMapping("/name")
    public ResponseEntity<Iterable<City>> search(@RequestParam("key")String key){
        Iterable<City>cities=cityService.findByNameContaining(key);
        return new ResponseEntity<>(cities,HttpStatus.OK);
    }
    @GetMapping("/city/{id}")
    public ResponseEntity<Optional<City>>findById(@PathVariable Long id){
        Optional<City> city= cityService.findById(id);
        return new ResponseEntity<>(city,HttpStatus.OK);
    }
}
