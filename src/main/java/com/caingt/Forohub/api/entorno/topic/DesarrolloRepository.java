package com.caingt.Forohub.api.entorno.topic;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DesarrolloRepository extends JpaRepository<Desarrollo, Long> {
    Page<Desarrollo> findByActiveTrue(Pageable pagination);
}
