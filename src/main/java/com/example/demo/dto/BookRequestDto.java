package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class BookRequestDto {
    @NotBlank(message = "the Title is required")
    @Size(min = 3, message = "the size must be 3 min")
    private String title;


    @NotBlank(message = "the Author is required")
    @Size(min = 2, message = "the size must be 2 min")
    private String author;
}
