package com.souzs.scb.domain.dtos;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LibraryUserDTO {
    // User
    @Valid
    private UserLoginDTO user;

    // Library
    @NotBlank(message = "Nome da biblioteca é obrigatório")
    @Size(min = 10, max = 150, message = "Nome deve ter entre 10 e 150 caracteres")
    private String name;

    @Size(min = 2, max = 10, message = "Nome deve ter entre 2 e 10 caracteres")
    private String acronym;

    // Address
    @Valid
    private AddressDTO address;
}
