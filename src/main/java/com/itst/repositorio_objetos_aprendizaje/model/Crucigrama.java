package com.itst.repositorio_objetos_aprendizaje.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "crucigrama")
public class Crucigrama {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCrucigrama;

    @Column(name = "contenido", columnDefinition = "TEXT")
    private String contenido;

    @ManyToOne
    @JoinColumn(name = "idGuion")
    private Guion guion;

    @OneToMany(mappedBy = "crucigrama", cascade = CascadeType.ALL)
    private List<ElementosCrucigrama> elementosCrucigrama;

    // Constructores
    public Crucigrama() {
    }

    public Crucigrama(String contenido, Guion guion) {
        this.contenido = contenido;
        this.guion = guion;
    }

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
