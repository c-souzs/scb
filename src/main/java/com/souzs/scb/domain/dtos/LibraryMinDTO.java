package com.souzs.scb.domain.dtos;

import com.souzs.scb.domain.entities.Library;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LibraryMinDTO {
    private Long id;
    private String name;
    private String acronym;

    public LibraryMinDTO(Library entity) {
        id = entity.getId();
        name = entity.getName();
        acronym = entity.getAcronym();
    }
}
