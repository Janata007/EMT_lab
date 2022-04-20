package com.example.library.service;

import com.example.library.models.Author;
import com.example.library.models.Country;
import java.util.List;
import java.util.Optional;

public interface AuthorService {
    Optional<Author> findById(Long id);

    List<Author> findAll();

    Author save(String name, String surname, Country country);

    void deleteAuthorById(Long id);
}
