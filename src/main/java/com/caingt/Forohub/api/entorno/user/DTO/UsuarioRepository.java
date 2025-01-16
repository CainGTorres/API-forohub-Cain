package com.caingt.Forohub.api.entorno.user.DTO;

import com.caingt.Forohub.api.entorno.user.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
    UserDetails findByEmail(String Email);
    Page<Usuario> findByActiveTrue(Pageable pagination);
}
