package com.example.springreactauth.controller;

import com.example.springreactauth.model.CartItem;
import com.example.springreactauth.repository.CartItemRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
@CrossOrigin(origins = "http://localhost:3000") // allow React frontend
public class CartController {

    private final CartItemRepository cartItemRepository;

    public CartController(CartItemRepository cartItemRepository) {
        this.cartItemRepository = cartItemRepository;
    }

    // Save confirmed cart items
    @PostMapping("/confirm")
    public List<CartItem> confirmCart(@RequestBody List<CartItem> cartItems) {
        return cartItemRepository.saveAll(cartItems);
    }

    // Get user cart items
    @GetMapping("/{userId}")
    public List<CartItem> getCart(@PathVariable Long userId) {
        return cartItemRepository.findByUserId(userId);
    }

    // Clear cart for a user
    @DeleteMapping("/clear/{userId}")
    public void clearCart(@PathVariable Long userId) {
        cartItemRepository.deleteByUserId(userId);
    }
}
