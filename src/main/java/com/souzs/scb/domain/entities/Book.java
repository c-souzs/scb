package com.souzs.scb.domain.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_book")
public class Book {
    @Id
    @Column(nullable = false)
    private Long isbn;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private LocalDate yearPublished;

    @Column(nullable = false, length = 500)
    private String summary;

    @Column(nullable = false)
    private String imageUrl;

    @OneToMany(mappedBy = "book")
    private List<BookAuthor> authors = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "tb_category_book",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private Set<BookCategory> categories = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "publisher_id")
    private Publisher publisher;

    @OneToMany(mappedBy = "book")
    private List<BookCopy> copies = new ArrayList<>();
}
