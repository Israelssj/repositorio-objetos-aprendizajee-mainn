package com.itst.repositorio_objetos_aprendizaje.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "videointeractivo")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idVideoInteractivo")
public class VideoInteractivo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idVideoInteractivo;

    @Column(name = "urlVideo", length = 255)
    private String urlVideo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idGuion")
    private Guion guion;

    @OneToMany(mappedBy = "videoInteractivo", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ElementosVideoInteractivo> elementosVideoInteractivo;

    // Getters y Setters
    public Integer getIdVideoInteractivo() {
        return idVideoInteractivo;
    }

    public void setIdVideoInteractivo(Integer idVideoInteractivo) {
        this.idVideoInteractivo = idVideoInteractivo;
    }

    public String getUrlVideo() {
        return urlVideo;
    }

    public void setUrlVideo(String urlVideo) {
        this.urlVideo = urlVideo;
    }

    public Guion getGuion() {
        return guion;
    }

    public void setGuion(Guion guion) {
        this.guion = guion;
    }

    public List<ElementosVideoInteractivo> getElementosVideoInteractivo() {
        return elementosVideoInteractivo;
    }

    public void setElementosVideoInteractivo(List<ElementosVideoInteractivo> elementosVideoInteractivo) {
        this.elementosVideoInteractivo = elementosVideoInteractivo;
    }
}
