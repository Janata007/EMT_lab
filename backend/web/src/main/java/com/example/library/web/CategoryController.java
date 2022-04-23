package com.example.library.web;

import com.example.library.service.BookService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    private final BookService bookService;

    public CategoryController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity<List<String>> getAllCategories() {
        List<String> categories = new ArrayList<>();
        categories = this.bookService.getAllCategories();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }
}
