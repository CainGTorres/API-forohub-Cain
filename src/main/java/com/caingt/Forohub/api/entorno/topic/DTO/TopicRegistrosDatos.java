package com.caingt.Forohub.api.entorno.topic.DTO;

import com.caingt.Forohub.api.entorno.response.Respuesta;
import com.caingt.Forohub.api.entorno.topic.Desarrollo;
import com.caingt.Forohub.api.entorno.user.Usuario;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;
import java.util.List;

public record TopicRegistrosDatos(
        @NotBlank(message = "Titulo ")
        String titulo,

        @NotBlank(message = "Mensaje ")
        @Size(min = 10, max = 1000, message = "El mensaje debe ser entre 10 y 1000 caracteres ")
        String mensaje,

        @NotNull(message = "Autor ")
        Usuario autor,

        @NotNull(message = "Desarrollo requerido ")
        Desarrollo desarrollo,

        @NotNull(message = "Creacion requerida ")
        LocalDateTime crearDatos,

        @NotNull(message = "Lista de respeustas requeridas ")
        List<Respuesta> respuesta

) {
}
