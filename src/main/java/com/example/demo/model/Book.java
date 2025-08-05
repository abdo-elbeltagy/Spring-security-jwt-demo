package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Book {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY )
    private Long id;


    @NotBlank(message = "the Title is required")
    @Size(min = 3,message = "the size must be 3 min")
    private String title;


    @NotBlank(message = "the Author is required")
    @Size(min = 2, message = "the size must be 2 min")
    private String author;
}
