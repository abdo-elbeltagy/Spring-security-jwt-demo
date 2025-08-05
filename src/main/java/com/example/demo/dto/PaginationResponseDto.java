package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class PaginationResponseDto<T> {
    List<T> data;
    private int currentPage;
    private int totalPages;
    private long totalElements;
}
