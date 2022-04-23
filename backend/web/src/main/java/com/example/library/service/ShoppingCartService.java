package com.example.library.service;

import com.example.library.models.Book;
import com.example.library.models.ShoppingCart;

import java.util.List;

public interface ShoppingCartService {
    List<Book> listAllBooksInShoppingCart(Long cartId);

    ShoppingCart getActiveShoppingCart(String username);

    ShoppingCart addBookToShoppingCart(String username, Long productId);

}
