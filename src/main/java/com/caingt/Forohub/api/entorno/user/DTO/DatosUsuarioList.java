package com.caingt.Forohub.api.entorno.user.DTO;

import com.caingt.Forohub.api.entorno.user.Perfil;
import com.caingt.Forohub.api.entorno.user.Usuario;

import java.util.List;

public record DatosUsuarioList(Long id, String nombre, String email, String password, List<Perfil> perfils) {
    public DatosUsuarioList(Usuario usuario){
        this(usuario.getId(), usuario.getNombre(), usuario.getEmail(), usuario.getPassword(), usuario.getProfiles());
    }
}
