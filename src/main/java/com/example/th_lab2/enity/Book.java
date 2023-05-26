package com.example.th_lab2.enity;

import com.example.th_lab2.validator.annotation.ValidCategoryId;
import com.example.th_lab2.validator.annotation.ValidUserId;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "title must be not empty")
    @Size(max = 50, min = 1, message = "title must be less than 50 characters")
    @Column(name = "title")
    private String title;

    @Column(name = "author")
    @Size(max = 50, message = "Author must be less than 50 characters")
    private String author;

    @Column(name = "price")
    @NotNull(message = "price is required")
    @Positive(message = "Price must be greater than 0")
    private Double price;

    @ManyToOne
    @JoinColumn(name = "category_id")
    @ValidCategoryId
    private Category category;

    @ManyToOne
    @JoinColumn(name = "user_id" ,referencedColumnName = "id")
    @ValidUserId
    private User user;
}
