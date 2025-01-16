package com.caingt.Forohub.api.entorno.user.DTO;

import com.caingt.Forohub.api.entorno.user.Perfil;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.List;

public record DatosRegistroUsuario(
        @NotBlank(message = "Nombre completo ")
        String nombre,

        @NotBlank(message = "Mail ")
        @Email(message = "El mail debe ser valido ")
        String email,

        @NotBlank(message = "Contraseña ")
        @Size(min = 8, max = 20, message = "La contraseña debe contener de entre 8 a 20 caracteres ")
        String password,

        @NotEmpty(message = "El perfil no puede estar vacio ")
        List<Perfil> perfils
) {
}
