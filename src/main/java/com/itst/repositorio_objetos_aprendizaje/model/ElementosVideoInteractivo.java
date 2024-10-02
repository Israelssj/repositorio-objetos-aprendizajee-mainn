package com.itst.repositorio_objetos_aprendizaje.model;

import jakarta.persistence.*;

@Entity
@Table(name = "elementosvideointeractivo")
public class ElementosVideoInteractivo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idElementosVideoInteractivo;

    @Column(name = "pregunta", columnDefinition = "TEXT")
    private String pregunta;

    @Column(name = "tipoPregunta")
    private String tipoPregunta;

    @Column(name = "minutoVideo")
    private String minutoVideo;

    @ManyToOne
    @JoinColumn(name = "idVideoInteractivo")
    private VideoInteractivo videoInteractivo;

    // Constructores
    public ElementosVideoInteractivo() {
        // Constructor vacío necesario para JPA
    }

    public ElementosVideoInteractivo(String pregunta, String tipoPregunta, String minutoVideo, VideoInteractivo videoInteractivo) {
        this.pregunta = pregunta;
        this.tipoPregunta = tipoPregunta;
        this.minutoVideo = minutoVideo;
        this.videoInteractivo = videoInteractivo;
    }

    // Getters y Setters
    public Integer getIdElementosVideoInteractivo() {
        return idElementosVideoInteractivo;
    }

    public void setIdElementosVideoInteractivo(Integer idElementosVideoInteractivo) {
        this.idElementosVideoInteractivo = idElementosVideoInteractivo;
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public String getTipoPregunta() {
        return tipoPregunta;
    }

    public void setTipoPregunta(String tipoPregunta) {
        this.tipoPregunta = tipoPregunta;
    }

    public String getMinutoVideo() {
        return minutoVideo;
    }

    public void setMinutoVideo(String minutoVideo) {
        this.minutoVideo = minutoVideo;
    }

    public VideoInteractivo getVideoInteractivo() {
        return videoInteractivo;
    }

    public void setVideoInteractivo(VideoInteractivo videoInteractivo) {
        this.videoInteractivo = videoInteractivo;
    }
}
