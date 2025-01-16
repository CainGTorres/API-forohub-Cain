package com.caingt.Forohub.api.entorno.topic.DTO;

import com.caingt.Forohub.api.entorno.topic.DesarrolloCategory;
import jakarta.validation.constraints.NotBlank;

public record DesarrolloDatosActualizacion(
        @NotBlank
        Long id,
        String nombre,
        DesarrolloCategory categoria
) {
}
