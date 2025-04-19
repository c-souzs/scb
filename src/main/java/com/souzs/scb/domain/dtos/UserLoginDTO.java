package com.souzs.scb.domain.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserLoginDTO {
    @NotBlank(message = "O e-mail é obrigatório.")
    @Email(message = "Formato de e-mail inválido.")
    private String email;

    @NotBlank(message = "A senha é obrigatória.")
    private String password;
}
