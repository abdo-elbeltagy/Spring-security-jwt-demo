package com.example.demo.controller;

import com.example.demo.dto.BookRequestDto;
import com.example.demo.dto.BookResponseDto;
import com.example.demo.dto.PaginationResponseDto;
import com.example.demo.service.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;


@RestController
@RequestMapping("/books")
public class BookController {
    @Autowired
    BookService bookService;

    @GetMapping
    public PaginationResponseDto<BookResponseDto> getAllBooks(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String author,
            @RequestParam(defaultValue = "id,asc") String[] sort
    ) {
        return bookService.getAllBooks(page, size, author, sort);
    }


    @GetMapping("/{id}")
    public ResponseEntity<BookResponseDto> getBookById(@PathVariable Long id) {
        return ResponseEntity.ok(bookService.getBookById(id));
    }

    @PostMapping
    public ResponseEntity<BookResponseDto> createBook(@Valid @RequestBody BookRequestDto bookRequestDto) {

        return ResponseEntity.status(HttpStatus.CREATED).body(bookService.createBook(bookRequestDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookResponseDto> updateBook(@PathVariable Long id, @Valid @RequestBody BookRequestDto bookRequestDto) {
        return ResponseEntity.ok(bookService.updateBook(id, bookRequestDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/decode")
    public ResponseEntity<String> decode() {
        String base64String = "eyJzdWIiOiJ1c2VyIiwicm9sZXMiOlt7ImF1dGhvcml0eSI6IlJPTEVfVVNFUiJ9LHsiYXV0aG9yaXR5IjoiUk9MRV9iYXRhdGVzIn1dLCJpYXQiOjE3NTQ1MDA4MzMsImV4cCI6MTc1NDUwNDQzM30";  // "Hello World!" in Base64

        // Decode the Base64 string
        byte[] decodedBytes = Base64.getDecoder().decode(base64String);

        // Convert bytes to string
        String decodedString = new String(decodedBytes);
        System.out.println(decodedString + " heeyyeye");

        // Print the result
        return ResponseEntity.ok(decodedString);
    }
}
