package com.souzs.scb.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_book_authorship")
public class BookAuthorship {
    @EmbeddedId
    private BookAuthorshipPK id;

    @ManyToOne
    @MapsId("book")
    private Book book;

    @ManyToOne
    @MapsId("author")
    private Author author;

    @ManyToOne
    @JoinColumn(name = "authorship_role_id", nullable = false)
    private AuthorshipRole role;
}
