package com.caingt.Forohub.api.entorno.response.DTO;

import com.caingt.Forohub.api.entorno.topic.Topic;
import com.caingt.Forohub.api.entorno.user.Usuario;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record RegistroDeRespuesta(
        @NotBlank(message = "La respuesta del mensaje es requerida ")
        String mensaje,

        @NotNull(message = "Topico requerido ")
        Topic topico,

        @NotNull(message = "Creación de la Data es requerido ")
        LocalDateTime creacionDeDatos,

        @NotNull(message = "Autor requerido ")
        Usuario autor,

        @NotBlank(message = "Estado de solucion requerida ")
        String solucion
){
}
