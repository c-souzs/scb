package com.souzs.scb.domain.dtos;

import com.souzs.scb.domain.entities.Library;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LibraryDTO {
    private Long id;
    private String name;
    private String acronym;
    private AddressDTO address;
    private List<OpeningHoursLibraryDTO> openingHoursLibrary;

    public LibraryDTO(Library entity) {
        id = entity.getId();
        name = entity.getName();
        acronym = entity.getAcronym();
        address = new AddressDTO(entity.getAddress());

        if(entity.getOpeningHours() != null) {
            entity.getOpeningHours()
                    .forEach(opHr -> openingHoursLibrary.add(new OpeningHoursLibraryDTO(opHr)));
        }
    }
}
