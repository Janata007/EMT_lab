package com.example.library.repository;

import com.example.library.models.Author;
import com.example.library.models.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    Page<Author> findAll(Pageable pageable);
}
