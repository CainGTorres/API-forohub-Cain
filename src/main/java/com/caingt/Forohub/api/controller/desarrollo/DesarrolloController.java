package com.caingt.Forohub.api.controller.desarrollo;

import com.caingt.Forohub.api.entorno.topic.Desarrollo;
import com.caingt.Forohub.api.entorno.topic.DesarrolloRepository;
import com.caingt.Forohub.api.entorno.topic.DTO.DesarrolloDataList;
import com.caingt.Forohub.api.entorno.topic.DTO.DesarrolloDatosRespuesta;
import com.caingt.Forohub.api.entorno.topic.DTO.DesarrolloDatosActualizacion;
import com.caingt.Forohub.api.entorno.topic.DTO.DesarrolloDatosDeRegistro;
import jakarta.transaction.Transactional;
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
@RequestMapping("/desarrollos")
public class DesarrolloController {

    @Autowired
    private DesarrolloRepository desarrolloRepository;

    @PostMapping
    public ResponseEntity<DesarrolloDatosRespuesta> registerCourse(@RequestBody @Valid DesarrolloDatosDeRegistro desarrolloDatosDeRegistro,
                                                                   UriComponentsBuilder uriComponentsBuilder) {
        Desarrollo desarrollo = desarrolloRepository.save(new Desarrollo(desarrolloDatosDeRegistro));
        DesarrolloDatosRespuesta desarrolloDatosRespuesta = new DesarrolloDatosRespuesta(
                desarrollo.getId(),
                desarrollo.getNombre(),
                desarrollo.getCategoria()
        );
        URI url = uriComponentsBuilder.path("/desarrollos/{id}").buildAndExpand(desarrollo.getId()).toUri();
        return ResponseEntity.created(url).body(desarrolloDatosRespuesta);
    }

    @GetMapping
    public ResponseEntity<Page<DesarrolloDataList>> listCourses(@PageableDefault(size = 2) Pageable pagination) {
        Page<Desarrollo> coursesPage = desarrolloRepository.findByActiveTrue(pagination);
        if (coursesPage.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        Page<DesarrolloDataList> courseDataLists = coursesPage.map(DesarrolloDataList::new);
        return ResponseEntity.ok(courseDataLists);
    }

    @PutMapping
    @Transactional
    public ResponseEntity updateCourses(@RequestBody @Valid DesarrolloDatosActualizacion desarrolloDatosActualizacion) {
        Desarrollo desarrollo = desarrolloRepository.getReferenceById(desarrolloDatosActualizacion.id());
        desarrollo.updateData(desarrolloDatosActualizacion);
        return ResponseEntity.ok(new DesarrolloDatosRespuesta(desarrollo.getId(), desarrollo.getNombre(), desarrollo.getCategoria()));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deleteCourses(@PathVariable Long id) {
        Desarrollo desarrollo = desarrolloRepository.getReferenceById(id);
        desarrollo.turnOffActive();
        return ResponseEntity.noContent().build();
    }
}
