package com.example.bookstore.service;

import com.example.bookstore.model.BookBasket;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BookStoreService {

    private static final double BOOK_PRICE = 50.0;

    public double calculatePrice(BookBasket basket) {
        List<String> books = new ArrayList<>(basket.getBooks());
        double totalPrice = 0;

        // Count the number of each book
        Map<String, Integer> bookCounts = new HashMap<>();
        for (String book : books) {
            bookCounts.put(book, bookCounts.getOrDefault(book, 0) + 1);
        }

        // Optimally group books for maximum discounts
        List<Integer> bookGroups = getOptimalGroups(bookCounts);

        // Calculate the total price for all groups
        for (int groupSize : bookGroups) {
            totalPrice += groupSize * BOOK_PRICE * (1 - getDiscount(groupSize));
        }

        return totalPrice;
       }

    private List<Integer> getOptimalGroups(Map<String, Integer> bookCounts) {

        List<Integer> groups = new ArrayList<>();

        while (!bookCounts.isEmpty()) {
            Set<String> currentGroup = new HashSet<>();

            for (String book : new HashSet<>(bookCounts.keySet())) {
                currentGroup.add(book);
                bookCounts.put(book, bookCounts.get(book) - 1);
                if (bookCounts.get(book) == 0) {
                    bookCounts.remove(book);
                }
            }

            groups.add(currentGroup.size());
        }

        // Reorganize groups to maximize discounts
        return reorganizeGroups(groups);
}

     private List<Integer> reorganizeGroups(List<Integer> groups) {

         // Count occurrences of each group size
         Map<Integer, Integer> groupCounts = new HashMap<>();
         for (int groupSize : groups) {
             groupCounts.put(groupSize, groupCounts.getOrDefault(groupSize, 0) + 1);
         }

         // Balance groups to maximize discounts
         while (groupCounts.getOrDefault(5, 0) > 0 && groupCounts.getOrDefault(3, 0) > 0) {
             // Convert a group of 5 and a group of 3 into two groups of 4
             groupCounts.put(5, groupCounts.get(5) - 1);
             groupCounts.put(3, groupCounts.get(3) - 1);
             groupCounts.put(4, groupCounts.getOrDefault(4, 0) + 2);
         }

         // Convert the map back into a list of group sizes
         List<Integer> balancedGroups = new ArrayList<>();
         for (Map.Entry<Integer, Integer> entry : groupCounts.entrySet()) {
             for (int i = 0; i < entry.getValue(); i++) {
                 balancedGroups.add(entry.getKey());
             }
         }
         return balancedGroups;
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


