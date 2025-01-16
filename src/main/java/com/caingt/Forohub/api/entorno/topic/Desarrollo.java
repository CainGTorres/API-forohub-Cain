package com.caingt.Forohub.api.entorno.topic;

import com.caingt.Forohub.api.entorno.topic.DTO.DesarrolloDatosActualizacion;
import com.caingt.Forohub.api.entorno.topic.DTO.DesarrolloDatosDeRegistro;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.*;

@Table(name= "desarrollos")
@Entity(name= "Desarrollo")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="id")
public class Desarrollo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @Enumerated(EnumType.STRING)
    private DesarrolloCategory categoria;

    private Boolean active;

    public Desarrollo(@Valid DesarrolloDatosDeRegistro desarrolloDatosDeRegistro) {
        this.active = true;
        this.nombre =  desarrolloDatosDeRegistro.nombre();
        this.categoria = desarrolloDatosDeRegistro.categoria();
    }

    public void updateData(DesarrolloDatosActualizacion desarrolloDatosActualizacion) {
        if (desarrolloDatosActualizacion.nombre() != null) {
            this.nombre = desarrolloDatosActualizacion.nombre();
        }
        if (desarrolloDatosActualizacion.categoria() != null) {
            this.categoria = desarrolloDatosActualizacion.categoria();
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


    public String getNombre() {
        return nombre;
    }

    public void setName(String name) {
        this.nombre = name;
    }

    public DesarrolloCategory getCategoria() {
        return categoria;
    }

    public void setCategory(DesarrolloCategory category) {
        this.categoria = category;
    }
}
