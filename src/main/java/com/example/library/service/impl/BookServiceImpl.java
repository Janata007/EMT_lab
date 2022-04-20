package com.example.library.service.impl;

import com.example.library.models.Author;
import com.example.library.models.Book;
import com.example.library.models.enums.CategoryEnum;
import com.example.library.repository.BookRepository;
import com.example.library.service.BookService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Optional<Book> findById(Long id) {
        return this.bookRepository.findById(id);
    }

    @Override
    public List<Book> findAll() {
        return this.bookRepository.findAll();
    }

    @Override
    public Book save(String name, CategoryEnum category, Author author, int availableCopies) {
        Book book = new Book(name, category, author, availableCopies);
        return this.bookRepository.save(book);
    }

    @Override
    public Book saveBook(Book book) {
        return this.bookRepository.save(book);
    }

    @Override
    public void deleteBookById(Long id) {
        this.bookRepository.deleteById(id);
    }

    @Override
    public List<String> getAllCategories() {
        List<String> categories = new ArrayList<>();
        categories.add(CategoryEnum.NOVEL.toString());
        categories.add(CategoryEnum.THRILLER.toString());
        categories.add(CategoryEnum.HISTORY.toString());
        categories.add(CategoryEnum.FANTASY.toString());
        categories.add(CategoryEnum.BIOGRAPHY.toString());
        categories.add(CategoryEnum.CLASSICS.toString());
        categories.add(CategoryEnum.DRAMA.toString());
        return categories;
    }

    @Override
    public List<Book> findByCategory(String category) {

       return this.bookRepository.findAllByCategory(CategoryEnum.valueOf(category));
    }
}
