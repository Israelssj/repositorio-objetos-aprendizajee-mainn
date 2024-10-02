package com.itst.repositorio_objetos_aprendizaje.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "evaluacion")
public class Evaluacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEvaluacion;

    @Column(name = "nota", nullable = false)
    private Integer nota;

    @Column(name = "comentario", columnDefinition = "TEXT")
    private String comentario;

    @OneToOne(mappedBy = "evaluacion", cascade = CascadeType.ALL)
    @JsonIgnore
    private Descarga descarga;

    @OneToOne(mappedBy = "evaluacion", cascade = CascadeType.ALL)
    @JsonIgnore
    private ObjetoAprendizaje objetoAprendizaje;

    // Constructores
    public Evaluacion() {
        // Constructor vacío necesario para JPA
    }

    public Evaluacion(Integer nota, String comentario) {
        this.nota = nota;
        this.comentario = comentario;
    }

    // Getters y Setters
    public Integer getIdEvaluacion() {
        return idEvaluacion;
    }

    public void setIdEvaluacion(Integer idEvaluacion) {
        this.idEvaluacion = idEvaluacion;
    }

    public Integer getNota() {
        return nota;
    }

    public void setNota(Integer nota) {
        this.nota = nota;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Descarga getDescarga() {
        return descarga;
    }

    public void setDescarga(Descarga descarga) {
        this.descarga = descarga;
    }

    public ObjetoAprendizaje getObjetoAprendizaje() {
        return objetoAprendizaje;
    }

    public void setObjetoAprendizaje(ObjetoAprendizaje objetoAprendizaje) {
        this.objetoAprendizaje = objetoAprendizaje;
    }
}
