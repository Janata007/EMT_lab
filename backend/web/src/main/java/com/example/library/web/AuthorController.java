package com.example.library.web;

import com.example.library.models.Author;
import com.example.library.models.Book;
import com.example.library.service.AuthorService;
import com.example.library.web.exceptions.AuthorNotFoundException;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api")
public class AuthorController {
    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("/authors")
    public ResponseEntity<List<Author>> getAllAuthors() {
        List<Author> authors = this.authorService.findAll();
        return new ResponseEntity<>(authors, HttpStatus.OK);
    }
    @GetMapping("/authors/pagination")
    public List<Author> getAlAuthorsWithPagination(Pageable pageable) {
        return this.authorService.findAllWithPagination(pageable).getContent();
    }

    @PostMapping("/authors")
    public ResponseEntity<Author> createAuthor(@RequestBody Author author) {
        try {
            Author _author = this.authorService.save(author.getName(), author.getSurname(), author.getCountry());
            return new ResponseEntity<>(author, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @GetMapping("/authors/{id}")
    public ResponseEntity<Author> findAuthorById(@PathVariable("id") long id) {
        try {
            Author author = this.authorService.findById(id).orElseThrow(() -> new AuthorNotFoundException(id));
            return new ResponseEntity<>(author, HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/authors/{id}")
    public ResponseEntity<HttpStatus> deleteAuthorById(@PathVariable("id") long id) {
        try {
            this.authorService.deleteAuthorById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
