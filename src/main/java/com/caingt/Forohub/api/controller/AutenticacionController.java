package com.caingt.Forohub.api.controller;

import com.caingt.Forohub.api.entorno.user.DTO.DatosUsuarioAutenticacion;
import com.caingt.Forohub.api.entorno.user.Usuario;
import com.caingt.Forohub.api.infra.seguridad.DTO.DataJWToken;
import com.caingt.Forohub.api.infra.seguridad.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class AutenticacionController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity autenticarUsuario(@RequestBody @Valid DatosUsuarioAutenticacion datosUsuarioAutenticacion){
        Authentication authenticationToken = new UsernamePasswordAuthenticationToken(datosUsuarioAutenticacion.email(), datosUsuarioAutenticacion.password());
        var userAuthenticated = authenticationManager.authenticate(authenticationToken);
        var JWToken =  tokenService.generate((Usuario) userAuthenticated.getPrincipal());
        return ResponseEntity.ok(new DataJWToken(JWToken));
    }
}
