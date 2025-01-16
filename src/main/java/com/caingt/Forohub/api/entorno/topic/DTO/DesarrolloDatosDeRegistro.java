package com.caingt.Forohub.api.entorno.topic.DTO;

import com.caingt.Forohub.api.entorno.topic.DesarrolloCategory;
import jakarta.validation.constraints.NotBlank;

public record DesarrolloDatosDeRegistro(
        @NotBlank(message = "Nombre de desarrollo es requerido ")
        String nombre,

        @NotBlank(message = "categoria de desarrollo es requerido ")
        DesarrolloCategory categoria
) {
}
