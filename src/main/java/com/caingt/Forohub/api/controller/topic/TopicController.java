package com.caingt.Forohub.api.controller.topic;

import com.caingt.Forohub.api.entorno.topic.DTO.DesarrolloDatosTopicosList;
import com.caingt.Forohub.api.entorno.topic.DTO.DatosRespuestaTopic;
import com.caingt.Forohub.api.entorno.topic.DTO.TopicActualizacionDatos;
import com.caingt.Forohub.api.entorno.topic.DTO.TopicRegistrosDatos;
import com.caingt.Forohub.api.entorno.topic.Topic;
import com.caingt.Forohub.api.entorno.topic.TopicRepository;
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
@RequestMapping("/topicos")
public class TopicController {

    @Autowired
    private TopicRepository topicRepository;

    @PostMapping
    public ResponseEntity<DatosRespuestaTopic> registerTopics(@RequestBody @Valid TopicRegistrosDatos topicRegistrosDatos, UriComponentsBuilder uriComponentsBuilder) {
        Topic topico = topicRepository.save(new Topic(topicRegistrosDatos));
        DatosRespuestaTopic datosRespuestaTopic = new DatosRespuestaTopic(
                topico.getId(),
                topico.getTitle(),
                topico.getMessage(),
                topico.getCreationDate(),
                topico.getAuthor(),
                topico.getCourse(),
                topico.getResponses()
        );
        URI url = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(url).body(datosRespuestaTopic);
    }

    @GetMapping
    public ResponseEntity<Page<DesarrolloDatosTopicosList>> listTopics(@PageableDefault(size = 2) Pageable pagination) {
        Page<Topic> topicPage = topicRepository.findByActiveTrue(pagination);
        if (topicPage.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        Page<DesarrolloDatosTopicosList> topicDataList = topicPage.map(DesarrolloDatosTopicosList::new);
        return ResponseEntity.ok(topicDataList);
    }

    @PutMapping
    @Transactional
    public ResponseEntity updateTopics(@RequestBody @Valid TopicActualizacionDatos topicActualizacionDatos) {
        Topic topic = topicRepository.getReferenceById(topicActualizacionDatos.id());
        topic.updateData(topicActualizacionDatos);
        return ResponseEntity.ok(new DatosRespuestaTopic(topic.getId(), topic.getTitle(), topic.getMessage(), topic.getCreationDate(), topic.getAuthor(), topic.getCourse(), topic.getResponses()));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deleteTopics(@PathVariable Long id) {
        Topic topic = topicRepository.getReferenceById(id);
        topic.turnOffActive();
        return ResponseEntity.noContent().build();
    }
}
