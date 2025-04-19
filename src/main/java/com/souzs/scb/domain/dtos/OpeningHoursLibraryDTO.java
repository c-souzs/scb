package com.souzs.scb.domain.dtos;

import com.souzs.scb.domain.entities.OpeningHoursLibrary;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.DayOfWeek;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OpeningHoursLibraryDTO {
    private Long id;

    @NotNull(message = "O dia da semana é obrigatório")
    private DayOfWeek day;

    @NotNull(message = "O horário de abertura é obrigatório")
    private LocalTime openingTime;

    @NotNull(message = "O horário de fechamento é obrigatório")
    private LocalTime closingTime;

    public OpeningHoursLibraryDTO(OpeningHoursLibrary entity) {
        id = entity.getId();
        day = entity.getDay();
        openingTime = entity.getOpeningTime();
        closingTime = entity.getClosingTime();
    }
}
