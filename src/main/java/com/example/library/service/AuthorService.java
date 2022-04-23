package com.example.library.service;

import com.example.library.models.Author;
import com.example.library.models.Country;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AuthorService {
    Optional<Author> findById(Long id);

    List<Author> findAll();

    Page<Author> findAllWithPagination(Pageable pageable);

    Author save(String name, String surname, Country country);

    void deleteAuthorById(Long id);
}
