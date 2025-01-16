package com.caingt.Forohub.api.entorno.topic.DTO;

import com.caingt.Forohub.api.entorno.response.Respuesta;
import com.caingt.Forohub.api.entorno.topic.Desarrollo;
import com.caingt.Forohub.api.entorno.user.Usuario;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;
import java.util.List;

public record TopicActualizacionDatos(
        @NotBlank
        Long id,
        String titulo,
        String mensaje,
        LocalDateTime crearDatos,
        Usuario autor,
        Desarrollo desarrollo,
        List<Respuesta> respuestas
) {
}
