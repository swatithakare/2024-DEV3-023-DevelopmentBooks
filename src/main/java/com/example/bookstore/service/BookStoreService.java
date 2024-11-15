package com.example.bookstore.service;

import com.example.bookstore.model.BookBasket;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
public class BookStoreService {

    private static final double BOOK_PRICE = 50.0;

    public double calculatePrice(BookBasket basket){
        int distinctBooks = new HashSet<>(basket.getBooks()).size();
        int totalBooks = basket.getBooks().size();

        double discount = getDiscount(distinctBooks);
        return distinctBooks * BOOK_PRICE * (1 - discount) + (totalBooks - distinctBooks) * BOOK_PRICE;
    }

    private double getDiscount(int distinctBooks){
        return switch (distinctBooks) {
            case 2 -> 0.05;
            case 3 -> 0.10;
            case 4 -> 0.20;
            case 5 -> 0.25;
            default -> 0.0;
        };
    }

}


