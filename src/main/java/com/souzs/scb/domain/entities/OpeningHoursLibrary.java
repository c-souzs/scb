package com.souzs.scb.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.DayOfWeek;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_opening_hours_library")
public class OpeningHoursLibrary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private DayOfWeek day;
    private LocalTime openingTime;
    private LocalTime closingTime;

    @ManyToOne
    @JoinColumn(name = "library_id")
    private Library library;
}
