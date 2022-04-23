package com.example.library.service;

import com.example.library.models.Author;
import com.example.library.models.Book;
import com.example.library.models.enums.CategoryEnum;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BookService {
    Optional<Book> findById(Long id);
    List<Book> findAll();
    Page<Book> findAllWithPagination(Pageable pageable);
    Book save(String name, CategoryEnum category, Author author, int copies);
    Book saveBook(Book book);
    void deleteBookById(Long id);
    List<String> getAllCategories();
    List<Book> findByCategory(String category);
    Book edit(Long id, Book book);
}
