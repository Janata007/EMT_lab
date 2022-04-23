package com.example.library.service.impl;

import com.example.library.models.Book;
import com.example.library.models.ShoppingCart;
import com.example.library.models.User;
import com.example.library.models.enums.ShoppingCartStatus;
import com.example.library.repository.ShoppingCartRepository;
import com.example.library.repository.UserRepository;
import com.example.library.service.BookService;
import com.example.library.service.ShoppingCartService;
import com.example.library.service.exceptions.BookAlreadyInShoppingCartException;
import com.example.library.service.exceptions.ShoppingCartNotFoundException;
import com.example.library.service.exceptions.UserNotFoundException;
import com.example.library.web.exceptions.BookNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private final ShoppingCartRepository shoppingCartRepository;
    private final UserRepository userRepository;
    private final BookService bookService;

    public ShoppingCartServiceImpl(ShoppingCartRepository shoppingCartRepository,
                                   UserRepository userRepository,
                                   BookService bookService) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.userRepository = userRepository;
        this.bookService = bookService;
    }

    @Override
    public List<Book> listAllBooksInShoppingCart(Long cartId) {
        if (!this.shoppingCartRepository.findById(cartId).isPresent())
            throw new ShoppingCartNotFoundException(cartId);
        return this.shoppingCartRepository.findById(cartId).get().getBooks();

    }

    @Override
    public ShoppingCart getActiveShoppingCart(String username) {
        User user = this.userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(username));

        return this.shoppingCartRepository
                .findByUserAndStatus(user, ShoppingCartStatus.CREATED)
                .orElseGet(() -> {
                    ShoppingCart cart = new ShoppingCart(user);
                    return this.shoppingCartRepository.save(cart);
                });

    }

    @Override
    public ShoppingCart addBookToShoppingCart(String username, Long productId) {
        ShoppingCart shoppingCart = this.getActiveShoppingCart(username);
        Book book = this.bookService.findById(productId)
                .orElseThrow(() -> new BookNotFoundException(productId));
        if (shoppingCart.getBooks()
                .stream().filter(i -> i.getId().equals(productId))
                .collect(Collectors.toList()).size() > 0)
            throw new BookAlreadyInShoppingCartException(productId, username);
        shoppingCart.getBooks().add(book);
        return this.shoppingCartRepository.save(shoppingCart);
    }

}
