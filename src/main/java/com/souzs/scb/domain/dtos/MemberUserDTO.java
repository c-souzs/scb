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
public class MemberUserDTO {
    // User
    @Valid
    private UserLoginDTO user;

    // Member
    @NotBlank(message = "Nome é obrigatório")
    @Size(max = 50, message = "Nome deve ter no máximo 50 caracteres")
    private String name;

    @NotBlank(message = "Sobrenome é obrigatório")
    @Size(max = 50, message = "Sobrenome deve ter no máximo 50 caracteres")
    private String surname;

    @NotBlank(message = "Número de telefone é obrigatório")
    private String phoneNumber;

    // Address
    @Valid
    private AddressDTO address;
}
