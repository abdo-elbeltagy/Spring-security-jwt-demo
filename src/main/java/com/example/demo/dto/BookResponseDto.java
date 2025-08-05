package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class BookResponseDto {
    private long id;
    private String title;
    private String author;
}
