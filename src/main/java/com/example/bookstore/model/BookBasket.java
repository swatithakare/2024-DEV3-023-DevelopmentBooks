package com.example.bookstore.model;

import java.util.ArrayList;
import java.util.List;

public class BookBasket {
    private final List<String> books = new ArrayList<>();

    public void addBook(String book){
        books.add(book);
    }

    public List<String> getBooks(){
        return books;
    }

}
