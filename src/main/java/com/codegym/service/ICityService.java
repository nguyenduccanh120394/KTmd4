package com.codegym.service;

import com.codegym.model.City;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ICityService extends IGeneralService<City> {
    Iterable<City>findByNameContaining(String name);
    Page<City> findAll(Pageable pageable);


}
