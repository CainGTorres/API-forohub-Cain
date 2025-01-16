package com.caingt.Forohub.api.controller.response;

import com.caingt.Forohub.api.entorno.response.DTO.ListaRespuestaDatos;
import com.caingt.Forohub.api.entorno.response.DTO.RespuestaDeDatos;
import com.caingt.Forohub.api.entorno.response.DTO.RespuestaActualizacion;
import com.caingt.Forohub.api.entorno.response.DTO.RegistroDeRespuesta;
import com.caingt.Forohub.api.entorno.response.Respuesta;
import com.caingt.Forohub.api.entorno.response.RespuestaRepository;
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
@RequestMapping("/respuestas")
public class RespuestaController {

    @Autowired
    private RespuestaRepository respuestaRepository;

    @PostMapping
    public ResponseEntity<RespuestaDeDatos> registerResponse(@RequestBody @Valid RegistroDeRespuesta registroDeRespuesta,
                                                             UriComponentsBuilder uriComponentsBuilder) {
        Respuesta respuesta = respuestaRepository.save(new Respuesta(registroDeRespuesta));
        RespuestaDeDatos respuestaDeDatos = new RespuestaDeDatos(
                respuesta.getId(),
                respuesta.getMessage(),
                respuesta.getTopic(),
                respuesta.getCreationDate(),
                respuesta.getAuthor(),
                respuesta.getSolution()
        );
        URI url = uriComponentsBuilder.path("/respuestas/{id}").buildAndExpand(respuesta.getId()).toUri();
        return ResponseEntity.created(url).body(respuestaDeDatos);
    }

    @GetMapping
    public ResponseEntity<Page<ListaRespuestaDatos>> listResponses(@PageableDefault(size = 2) Pageable pagination) {
        Page<Respuesta> responsesPage = respuestaRepository.findByActiveTrue(pagination);
        if (responsesPage.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        Page<ListaRespuestaDatos> responseDataLists = responsesPage.map(ListaRespuestaDatos::new);
        return ResponseEntity.ok(responseDataLists);
    }

    @PutMapping
    @Transactional
    public ResponseEntity updateResponses(@RequestBody @Valid RespuestaActualizacion respuestaActualizacion) {
        Respuesta respuesta = respuestaRepository.getReferenceById(respuestaActualizacion.id());
        respuesta.updateData(respuestaActualizacion);
        return ResponseEntity.ok(new RespuestaDeDatos(respuesta.getId(), respuesta.getMessage(), respuesta.getTopic(), respuesta.getCreationDate(), respuesta.getAuthor(), respuesta.getSolution()));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deleteResponses(@PathVariable Long id) {
        Respuesta respuesta = respuestaRepository.getReferenceById(id);
        respuesta.turnOffActive();
        return ResponseEntity.noContent().build();
    }
}
