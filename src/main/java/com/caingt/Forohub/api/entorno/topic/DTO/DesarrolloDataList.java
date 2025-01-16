package com.caingt.Forohub.api.entorno.topic.DTO;

import com.caingt.Forohub.api.entorno.topic.Desarrollo;
import com.caingt.Forohub.api.entorno.topic.DesarrolloCategory;

public record DesarrolloDataList(Long id, String nombre, DesarrolloCategory categoria) {
    public DesarrolloDataList(Desarrollo desarrollo){
        this(desarrollo.getId(), desarrollo.getNombre(), desarrollo.getCategoria());
    }
}
