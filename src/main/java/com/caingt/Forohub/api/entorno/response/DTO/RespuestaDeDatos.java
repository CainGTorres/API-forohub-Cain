package com.caingt.Forohub.api.entorno.response.DTO;

import com.caingt.Forohub.api.entorno.topic.Topic;
import com.caingt.Forohub.api.entorno.user.Usuario;

import java.time.LocalDateTime;

public record RespuestaDeDatos(
        Long id,
        String mensaje,
        Topic topico,
        LocalDateTime creacionDeDatos,
        Usuario autor,
        String solucion
) {
}
