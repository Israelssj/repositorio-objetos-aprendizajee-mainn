package com.itst.repositorio_objetos_aprendizaje.model;

import jakarta.persistence.*;

@Entity
@Table(name = "diapositiva")
public class Diapositiva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idDiapositiva;

    @Column(name = "descripcion", columnDefinition = "TEXT")
    private String descripcion;

    @Column(name = "interacciones", columnDefinition = "TEXT")
    private String interacciones;

    @ManyToOne
    @JoinColumn(name = "idPresentacionCurso")
    private PresentacionCurso presentacionCurso;

    // Constructores
    public Diapositiva() {
        // Constructor vacío necesario para JPA
    }

    public Diapositiva(String descripcion, String interacciones, PresentacionCurso presentacionCurso) {
        this.descripcion = descripcion;
        this.interacciones = interacciones;
        this.presentacionCurso = presentacionCurso;
    }

    // Getters y Setters
    public Integer getIdDiapositiva() {
        return idDiapositiva;
    }

    public void setIdDiapositiva(Integer idDiapositiva) {
        this.idDiapositiva = idDiapositiva;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getInteracciones() {
        return interacciones;
    }

    public void setInteracciones(String interacciones) {
        this.interacciones = interacciones;
    }

    public PresentacionCurso getPresentacionCurso() {
        return presentacionCurso;
    }

    public void setPresentacionCurso(PresentacionCurso presentacionCurso) {
        this.presentacionCurso = presentacionCurso;
    }
}
