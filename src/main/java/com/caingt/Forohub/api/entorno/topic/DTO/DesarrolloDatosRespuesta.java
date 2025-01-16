package com.caingt.Forohub.api.entorno.topic.DTO;

import com.caingt.Forohub.api.entorno.topic.DesarrolloCategory;

public record DesarrolloDatosRespuesta(
        Long id,
        String nombre,
        DesarrolloCategory categoria
) {
}
