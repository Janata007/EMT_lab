package com.example.library.repository;

import com.example.library.models.Book;
import com.example.library.models.enums.CategoryEnum;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findAllByCategory(CategoryEnum category);

    Book findByName(String name);

    void deleteByName(String name);

    Page<Book> findAll(Pageable pageable);
}
