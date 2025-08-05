package com.example.demo.service;

import com.example.demo.dto.BookRequestDto;
import com.example.demo.dto.BookResponseDto;
import com.example.demo.dto.PaginationResponseDto;
import com.example.demo.mapper.BookMapper;
import com.example.demo.model.Book;
import com.example.demo.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.List;


@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public PaginationResponseDto<BookResponseDto> getAllBooks(int page, int size,String author, String [] sort) {
        System.out.println(Arrays.toString(sort));

        Sort sortObj = Sort.by(
                new Sort.Order(sort[1].equals("desc")?Sort.Direction.DESC:Sort.Direction.ASC, sort[0])
        );

        Pageable pageable = PageRequest.of(page,size,sortObj);
        Page <Book> bookPage;
        if(author!=null && !author.isEmpty()){
            bookPage = bookRepository.findByAuthorContainingIgnoreCase(author, pageable);
        }
        else {
            bookPage = bookRepository.findAll(pageable);
        }


        List<BookResponseDto> books = bookPage.stream().map(BookMapper::toDto).toList();
        return new PaginationResponseDto<>(books,bookPage.getNumber(), bookPage.getTotalPages(), bookPage.getTotalElements());
    }

    public BookResponseDto getBookById(Long id) {
        Book book = bookRepository.findById(id).
                orElseThrow(() -> new RuntimeException("the book to get doesn't exist"));
        return BookMapper.toDto(book);
    }

    public BookResponseDto createBook(BookRequestDto bookRequestDto) {
        Book savedBook = BookMapper.toBook(bookRequestDto);
        bookRepository.save(savedBook);
        return BookMapper.toDto(savedBook);
    }

    public BookResponseDto updateBook(Long id, BookRequestDto bookRequestDto) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book to update not found"));
        book.setAuthor(bookRequestDto.getAuthor());
        book.setTitle(bookRequestDto.getTitle());
        bookRepository.save(book);
        return BookMapper.toDto(book);
    }

    public void deleteBook(Long id){
        bookRepository.deleteById(id);
    }
}
