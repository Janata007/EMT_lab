package com.example.library.models;

import com.example.library.models.enums.CategoryEnum;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "book")
@AllArgsConstructor
@Data
@Getter
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @Enumerated(EnumType.STRING)
    private CategoryEnum category;
    @ManyToOne
    private Author author;
    private int copies;

    public Book(String name, CategoryEnum category, Author author, int copies) {
        this.name = name;
        this.category = category;
        this.author = author;
        this.copies = copies;
    }
    public List<String> getAllCategories(){
        List<String> categories = new ArrayList<>();
        categories.add("NOVEL");
        categories.add("THRILLER");
        categories.add("HISTORY");
        categories.add("FANTASY");
        categories.add("BIOGRAPHY");
        categories.add("CLASSICS");
        categories.add("DRAMA");
        return categories;
    }
}
