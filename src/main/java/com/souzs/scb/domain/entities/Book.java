package com.souzs.scb.domain.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

    @Column(nullable = false)
    private String summary;

    @Column(nullable = false)
    private String imageUrl;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    @Setter(AccessLevel.NONE)
    private List<BookAuthorship> authors = new ArrayList<>();

    public void addAuthor(BookAuthorship item) {
        authors.add(item);
    }
}
