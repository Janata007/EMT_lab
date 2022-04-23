package com.example.library.service.impl;

import com.example.library.models.Author;
import com.example.library.models.Country;
import com.example.library.repository.AuthorRepository;
import com.example.library.service.AuthorService;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public Optional<Author> findById(Long id) {
        return this.authorRepository.findById(id);
    }

    @Override
    public List<Author> findAll() {
        return this.authorRepository.findAll();
    }

    @Override
    public Page<Author> findAllWithPagination(Pageable pageable) {
        return this.authorRepository.findAll(pageable);
    }

    @Override
    public Author save(String name, String surname, Country country) {
        Author author = new Author(name, surname, country);
        return this.authorRepository.save(author);
    }

    @Override
    public void deleteAuthorById(Long id) {
        this.authorRepository.deleteById(id);
    }
}
