package com.example.bookstore.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class BookStoreControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldReturnCalculatedPrice() throws Exception {
        String basketJson = "{\"books\": [\"Clean Code\", \"Clean Coder\"]}";

        mockMvc.perform(post("/api/v1/bookstore/calculate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(basketJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price").value(95.0));
    }
}
