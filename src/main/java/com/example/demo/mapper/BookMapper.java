package com.example.demo.mapper;

import com.example.demo.dto.BookRequestDto;
import com.example.demo.dto.BookResponseDto;
import com.example.demo.model.Book;

public class BookMapper {
    public static Book toBook (BookRequestDto bookRequestDto){
        Book book = Book.builder()
                .author(bookRequestDto.getAuthor())
                .title(bookRequestDto.getTitle())
                .build();
        return book;
    }
    public static BookResponseDto toDto(Book book){
        return new BookResponseDto(book.getId(), book.getTitle(), book.getAuthor());
    }
}
