package com.example.library.models.dto;

import com.example.library.models.Author;
import com.example.library.models.enums.CategoryEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDto {
    private String name;
    private CategoryEnum category;
    private Author author;
    private int copies;
}
