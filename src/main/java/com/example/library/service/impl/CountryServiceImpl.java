package com.example.library.service.impl;

import com.example.library.models.Country;
import com.example.library.repository.CountryRepository;
import com.example.library.service.CountryService;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class CountryServiceImpl implements CountryService {
    private final CountryRepository countryRepository;

    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public Optional<Country> findById(Long id) {
        return this.countryRepository.findById(id);
    }

    @Override
    public List<Country> findAll() {
        return this.countryRepository.findAll();
    }

    @Override
    public Country save(String name, String continent) {
        Country country = new Country(name, continent);
        return this.countryRepository.save(country);
    }

    @Override
    public void deleteCountryById(Long id) {
        this.countryRepository.deleteById(id);
    }
}
