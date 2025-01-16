package com.caingt.Forohub.api.entorno.topic;

import com.caingt.Forohub.api.entorno.response.Respuesta;
import com.caingt.Forohub.api.entorno.topic.DTO.TopicActualizacionDatos;
import com.caingt.Forohub.api.entorno.topic.DTO.TopicRegistrosDatos;
import com.caingt.Forohub.api.entorno.user.Usuario;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Table(name="topicos")
@Entity(name="Topico")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensaje;


    private LocalDateTime crearDatos;

    private Boolean active;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "autor_id")
    private Usuario autor;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "course_id")
    private Desarrollo desarrollo;

    @OneToMany(mappedBy = "topico", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Respuesta> respeusta;

    public Topic(@Valid TopicRegistrosDatos topicRegistrosDatos) {
        this.active = true;
        this.titulo =  topicRegistrosDatos.titulo();
        this.mensaje = topicRegistrosDatos.mensaje();
        this.crearDatos = topicRegistrosDatos.crearDatos();
        this.autor = topicRegistrosDatos.autor();
        this.desarrollo = topicRegistrosDatos.desarrollo();
        this.respeusta = topicRegistrosDatos.respuesta();
    }

    public void updateData(TopicActualizacionDatos topicActualizacionDatos) {
        if (topicActualizacionDatos.titulo() != null) {
            this.titulo = topicActualizacionDatos.titulo();
        }
        if (topicActualizacionDatos.mensaje() != null) {
            this.mensaje = topicActualizacionDatos.mensaje();
        }
        if(topicActualizacionDatos.crearDatos() != null){
            this.crearDatos = topicActualizacionDatos.crearDatos();
        }
        if(topicActualizacionDatos.autor() != null){
            this.autor = topicActualizacionDatos.autor();
        }
        if(topicActualizacionDatos.desarrollo() != null){
            this.desarrollo = topicActualizacionDatos.desarrollo();
        }
        if(topicActualizacionDatos.respuestas() != null){
            this.respeusta = topicActualizacionDatos.respuestas();
        }
    }

    public void turnOffActive() {
        this.active = false;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return titulo;
    }

    public String getMessage() {
        return mensaje;
    }

    public void setMessage(String message) {
        this.mensaje = message;
    }

    public LocalDateTime getCreationDate() {
        return crearDatos;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.crearDatos = creationDate;
    }

    public Usuario getAuthor() {
        return autor;
    }

    public void setAuthor(Usuario author) {
        this.autor = author;
    }



    public List<Respuesta> getResponses() {
        return respeusta;
    }

    public void setResponses(List<Respuesta> responses) {
        this.respeusta = responses;
    }

    public Desarrollo getCourse() {
        return desarrollo;
    }

    public void setCourse(Desarrollo desarrollo) {
        this.desarrollo = desarrollo;
    }
}
