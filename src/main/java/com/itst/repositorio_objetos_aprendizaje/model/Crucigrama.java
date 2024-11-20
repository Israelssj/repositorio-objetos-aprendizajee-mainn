package com.itst.repositorio_objetos_aprendizaje.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "crucigrama")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idCrucigrama")
public class Crucigrama {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCrucigrama;

    @Column(name = "contenido", columnDefinition = "TEXT")
    private String contenido;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idGuion")
    private Guion guion;

    @OneToMany(mappedBy = "crucigrama", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ElementosCrucigrama> elementosCrucigrama;

    // Getters y Setters
    public Integer getIdCrucigrama() {
        return idCrucigrama;
    }

    public void setIdCrucigrama(Integer idCrucigrama) {
        this.idCrucigrama = idCrucigrama;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public Guion getGuion() {
        return guion;
    }

    public void setGuion(Guion guion) {
        this.guion = guion;
    }

    public List<ElementosCrucigrama> getElementosCrucigrama() {
        return elementosCrucigrama;
    }

    public void setElementosCrucigrama(List<ElementosCrucigrama> elementosCrucigrama) {
        this.elementosCrucigrama = elementosCrucigrama;
    }
}
