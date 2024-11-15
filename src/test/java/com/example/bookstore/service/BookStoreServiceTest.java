package com.example.bookstore.service;


import com.example.bookstore.model.BookBasket;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
public class BookStoreServiceTest {

    @Autowired
    private BookStoreService bookStoreService;

    @Test
    void shouldCalculatePriceForSingleBook() {
        BookBasket basket = new BookBasket();
        basket.addBook("Clean Code");
        double price = bookStoreService.calculatePrice(basket);

        assertEquals(50.0, price, "Price for a single book should be 50 EUR");
    }

    @Test
    void shouldApplyFivePercentDiscountForTwoDifferentBooks(){

        BookBasket basket = new BookBasket();
        basket.addBook("Clean Code");
        basket.addBook("The Clean Coder");

        double price = bookStoreService.calculatePrice(basket);

        assertEquals(95.0, price, "Price for two different books be 95 EUR");
    }

}
