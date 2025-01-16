package com.caingt.Forohub.api.entorno.response.DTO;

import com.caingt.Forohub.api.entorno.response.Respuesta;
import com.caingt.Forohub.api.entorno.topic.Topic;
import com.caingt.Forohub.api.entorno.user.Usuario;

import java.time.LocalDateTime;

public record ListaRespuestaDatos(Long id, String mensaje, Topic topico, LocalDateTime crearDatos, Usuario autor, String solucion) {
    public ListaRespuestaDatos(Respuesta respuesta){
        this(respuesta.getId(), respuesta.getMessage(), respuesta.getTopic(), respuesta.getCreationDate(), respuesta.getAuthor(), respuesta.getSolution());
    }
}
