package com.example.bookstore.service;

import com.example.bookstore.model.BookBasket;
import org.springframework.stereotype.Service;

@Service
public class BookStoreService {

    private static final double BOOK_PRICE = 50.0;

    public double calculatePrice(BookBasket basket){
        return basket.getBooks().size() * BOOK_PRICE;
    }

}


