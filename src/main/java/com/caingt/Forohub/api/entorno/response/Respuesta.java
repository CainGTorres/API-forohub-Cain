package com.caingt.Forohub.api.entorno.response;

import com.caingt.Forohub.api.entorno.response.DTO.RespuestaActualizacion;
import com.caingt.Forohub.api.entorno.response.DTO.RegistroDeRespuesta;
import com.caingt.Forohub.api.entorno.topic.Topic;
import com.caingt.Forohub.api.entorno.user.Usuario;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.*;

import java.time.LocalDateTime;

@Table(name = "respuestas")
@Entity(name =  "Respuesta")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Respuesta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String mensaje;

    private Boolean active;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topic_id")
    private Topic topico;

    @Column(name = "creacionDeDatos", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime creacionDeDatos;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "autor_id")
    private Usuario autor;
    private String solucion;

    public Respuesta(@Valid RegistroDeRespuesta registroDeRespuesta) {
        this.active = true;
        this.mensaje =  registroDeRespuesta.mensaje();
        this.topico = registroDeRespuesta.topico();
        this.creacionDeDatos = registroDeRespuesta.creacionDeDatos();
        this.autor = registroDeRespuesta.autor();
        this.solucion = registroDeRespuesta.solucion();
    }

    public void updateData(RespuestaActualizacion respuestaActualizacion) {
        if (respuestaActualizacion.mensaje() != null) {
            this.mensaje = respuestaActualizacion.mensaje();
        }
        if (respuestaActualizacion.topico() != null) {
            this.topico = respuestaActualizacion.topico();
        }
        if(respuestaActualizacion.creacionDeDatos() != null){
            this.creacionDeDatos = respuestaActualizacion.creacionDeDatos();
        }
        if(respuestaActualizacion.autor() != null){
            this.autor = respuestaActualizacion.autor();
        }
        if(respuestaActualizacion.solucion() != null){
            this.solucion  =  respuestaActualizacion.solucion();
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

    public String getMessage() {
        return mensaje;
    }

    public void setMessage(String message) {
        this.mensaje = message;
    }

    public Topic getTopic() {
        return topico;
    }

    public void setTopic(Topic topic) {
        this.topico = topic;
    }

    public LocalDateTime getCreationDate() {
        return creacionDeDatos;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creacionDeDatos = creationDate;
    }

    public Usuario getAuthor() {
        return autor;
    }

    public void setAuthor(Usuario author) {
        this.autor = author;
    }

    public String getSolution() {
        return solucion;
    }

    public void setSolution(String solution) {
        this.solucion = solution;
    }
}
