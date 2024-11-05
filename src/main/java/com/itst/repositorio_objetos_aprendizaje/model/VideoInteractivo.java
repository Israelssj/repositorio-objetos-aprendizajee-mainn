package com.itst.repositorio_objetos_aprendizaje.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "videointeractivo")
public class VideoInteractivo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idVideoInteractivo;

    @Column(name = "urlVideo", length = 255)
    private String urlVideo;

    @ManyToOne
    @JoinColumn(name = "idGuion")
    @JsonIgnoreProperties("videosInteractivos")
    private Guion guion;

    @OneToMany(mappedBy = "videoInteractivo", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("videoInteractivo")
    private List<ElementosVideoInteractivo> elementosVideoInteractivo;

    // Constructores
    public VideoInteractivo() {
    }

    public VideoInteractivo(String urlVideo, Guion guion) {
        this.urlVideo = urlVideo;
        this.guion = guion;
    }

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
