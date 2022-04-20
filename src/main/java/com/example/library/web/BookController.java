package com.example.library.web;

import com.example.library.models.Book;
import com.example.library.service.BookService;
import com.example.library.web.exceptions.BookNotFoundException;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/books", "/"})
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books = this.bookService.findAll();
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        try {
            Book _book =
                this.bookService.save(book.getName(), book.getCategory(), book.getAuthor(), book.getAvailableCopies());
            return new ResponseEntity<>(book, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> findBookById(@PathVariable("id") long id) {
        try {
            Book book = this.bookService.findById(id).orElseThrow(() -> new BookNotFoundException(id));
            return new ResponseEntity<>(book, HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{category}")
    public ResponseEntity<List<Book>> findBooksByCategory(@PathVariable("category") String category) {
        try {
            List<Book> books = this.bookService.findByCategory(category);
            return new ResponseEntity<>(books, HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteBookById(@PathVariable("id") long id) {
        try {
            this.bookService.deleteBookById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/take/{id}")
    public ResponseEntity<Book> markBookAsTaken(@PathVariable("id") long id) {
        try {
            Book book = this.bookService.findById(id).orElseThrow(() -> new BookNotFoundException(id));
            book.setAvailableCopies(book.getAvailableCopies() - 1);
            this.bookService.saveBook(book);
            return new ResponseEntity<>(book, HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
