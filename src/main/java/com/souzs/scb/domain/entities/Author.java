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
@Table(name = "tb_author")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String surname;

    @Column(nullable = false)
    private LocalDate dateBirth;

    @Column(nullable = false)
    private LocalDate dateDeath;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    @Setter(AccessLevel.NONE)
    private List<BookAuthorship> books = new ArrayList<>();

    public void addBook(BookAuthorship item) {
        books.add(item);
    }
}
