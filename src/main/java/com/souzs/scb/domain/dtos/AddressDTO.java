package com.souzs.scb.domain.dtos;

import com.souzs.scb.domain.entities.Address;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDTO {
    private Long id;

    @NotBlank(message = "Cidade é obrigatória")
    @Size(max = 100, message = "Cidade deve ter no máximo 100 caracteres")
    private String city;

    @NotBlank(message = "Estado é obrigatório")
    @Size(min = 2, max = 2, message = "Estado deve conter exatamente 2 letras (UF)")
    private String state;

    @NotBlank(message = "Logradouro é obrigatório")
    @Size(max = 150, message = "Logradouro deve ter no máximo 150 caracteres")
    private String road;

    @NotBlank(message = "Número é obrigatório")
    @Size(max = 10, message = "Número deve ter no máximo 10 caracteres")
    private String number;

    @NotBlank(message = "CEP é obrigatório")
    private String cep;

    public AddressDTO(Address entity) {
        id = entity.getId();
        city = entity.getCity();
        state = entity.getState();
        road = entity.getRoad();
        number = String.valueOf(entity.getNumber());
        cep = entity.getCep();
    }
}
