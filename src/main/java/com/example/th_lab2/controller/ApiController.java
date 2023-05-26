package com.example.th_lab2.controller;

import com.example.th_lab2.dto.BookDto;
import com.example.th_lab2.enity.Book;
import com.example.th_lab2.services.BookService;
import com.example.th_lab2.services.CategoryService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/books")
public class ApiController {
    @Autowired
    private BookService bookService;
    @Autowired
    private CategoryService categoryService;
    private BookDto convertToBookDto (Book book) {
        BookDto bookDTO = new BookDto();
        bookDTO.setId(book.getId());
        bookDTO.setTitle (book.getTitle());
        bookDTO.setAuthor (book.getAuthor());
        bookDTO.setPrice (book.getPrice());
        bookDTO.setCategoryName (categoryService.getCategoryById(book.getCategory().getId()).getName());
        return bookDTO;
    }
    @GetMapping
    @ResponseBody
    public List<BookDto> getAllBooks() {
        List<Book> books = bookService.getAllBooks();
        bookService.getAllBooks();
        List<BookDto> bookDTOs = new ArrayList<>();
        for (Book book : books) {
            bookDTOs.add(convertToBookDto(book));
        }
        return bookDTOs;
    }
    @GetMapping("/{id}")
    @ResponseBody
    public BookDto getBookById(@PathVariable Long id) {
        Book book = bookService.getBookById(id);
        return convertToBookDto(book);
    }
    @DeleteMapping("/{id}")
    @Transactional
    public void deleteBook (@PathVariable Long id) {
        if (bookService.getBookById(id) != null)
            bookService.deleteBook(id);
    }
}
