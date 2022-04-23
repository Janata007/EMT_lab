package com.example.library.service.impl;

import com.example.library.models.Author;
import com.example.library.models.Book;
import com.example.library.models.enums.CategoryEnum;
import com.example.library.repository.BookRepository;
import com.example.library.service.BookService;
import com.example.library.web.exceptions.BookNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public Page<Book> findAllWithPagination(Pageable pageable) {
        return this.bookRepository.findAll(pageable);
    }

    @Override
    public Book save(String name, CategoryEnum category, Author author, int copies) {
        Book book = new Book(name, category, author, copies);
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

    @Override
    public Book edit(Long id, Book book) {
        Book book1 = this.bookRepository.findById(id)
            .orElseThrow(() -> new BookNotFoundException(id));
        book1.setCopies(book.getCopies());
        book1.setAuthor(book.getAuthor());
        book1.setCategory(book.getCategory());
        book1.setName(book.getName());
        return this.bookRepository.save(book1);
    }

}
