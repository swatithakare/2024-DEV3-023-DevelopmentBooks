package com.example.bookstore.controller;

import com.example.bookstore.dto.PriceResponse;
import com.example.bookstore.model.BookBasket;
import com.example.bookstore.service.BookStoreService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/bookstore")
public class BookStoreController {

    private final BookStoreService bookStoreService;

    public BookStoreController(BookStoreService bookStoreService) {
        this.bookStoreService = bookStoreService;
    }

    @PostMapping("/calculate")
    public PriceResponse calculatePrice(@RequestBody BookBasket basket) {
        double price = bookStoreService.calculatePrice(basket);
        return new PriceResponse(price);
    }
}