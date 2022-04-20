package com.example.library.service;

import com.example.library.models.Country;
import java.util.List;
import java.util.Optional;

public interface CountryService {
    Optional<Country> findById(Long id);

    List<Country> findAll();

    Country save(String name, String continent);

    void deleteCountryById(Long id);
}
