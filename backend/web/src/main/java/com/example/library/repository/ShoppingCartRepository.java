package com.example.library.repository;

import com.example.library.models.ShoppingCart;
import com.example.library.models.User;
import com.example.library.models.enums.ShoppingCartStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {
    Optional<ShoppingCart> findByUserAndStatus(User user, ShoppingCartStatus status);
}
