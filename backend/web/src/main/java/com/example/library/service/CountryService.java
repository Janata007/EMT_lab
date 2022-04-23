package com.example.library.service;

import com.example.library.models.Country;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CountryService {
    Optional<Country> findById(Long id);

    List<Country> findAll();

    Page<Country> findAllWithPagination(Pageable pageable);

    Country save(String name, String continent);

    void deleteCountryById(Long id);
}
