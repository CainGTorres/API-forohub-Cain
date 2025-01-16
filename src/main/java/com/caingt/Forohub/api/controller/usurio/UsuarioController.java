package com.caingt.Forohub.api.controller.usurio;

import com.caingt.Forohub.api.entorno.user.DTO.DatosUsuarioList;
import com.caingt.Forohub.api.entorno.user.DTO.DatosUsuarioRespuesta;
import com.caingt.Forohub.api.entorno.user.DTO.DatosRegistroUsuario;
import com.caingt.Forohub.api.entorno.user.DTO.UsuarioRepository;
import com.caingt.Forohub.api.entorno.user.Usuario;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    public ResponseEntity<DatosUsuarioRespuesta> registerUser(@RequestBody @Valid DatosRegistroUsuario datosRegistroUsuario,
                                                              UriComponentsBuilder uriComponentsBuilder) {
        Usuario usuario = usuarioRepository.save(new Usuario(datosRegistroUsuario));
        DatosUsuarioRespuesta datosUsuarioRespuesta = new DatosUsuarioRespuesta(
                usuario.getId(),
                usuario.getNombre(),
                usuario.getEmail()
        );
        URI url = uriComponentsBuilder.path("/users/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(url).body(datosUsuarioRespuesta);
    }

    @GetMapping
    public ResponseEntity<Page<DatosUsuarioList>> listUsers(@PageableDefault(size = 2) Pageable pagination) {
        Page<Usuario> usersPage = usuarioRepository.findByActiveTrue(pagination);
        if (usersPage.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        Page<DatosUsuarioList> userDataListPage = usersPage.map(DatosUsuarioList::new);
        return ResponseEntity.ok(userDataListPage);
    }

}
