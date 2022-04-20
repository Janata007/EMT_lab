package com.example.library.service;

import com.example.library.models.Author;
import com.example.library.models.Book;
import com.example.library.models.enums.CategoryEnum;
import java.util.List;
import java.util.Optional;

public interface BookService {
    Optional<Book> findById(Long id);
    List<Book> findAll();
    Book save(String name, CategoryEnum category, Author author, int availableCopies);
    Book saveBook(Book book);
    void deleteBookById(Long id);
    List<String> getAllCategories();
    List<Book> findByCategory(String category);
}
