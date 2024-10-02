package com.itst.repositorio_objetos_aprendizaje.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "presentacioncurso")
public class PresentacionCurso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPresentacionCurso;

    @ManyToOne
    @JoinColumn(name = "idGuion")
    private Guion guion;

    @OneToMany(mappedBy = "presentacionCurso", cascade = CascadeType.ALL)
    private List<Diapositiva> diapositivas;

    // Constructores
    public PresentacionCurso() {
        // Constructor vacío necesario para JPA
    }


    public PresentacionCurso(Guion guion) {
        this.guion = guion;
    }

    // Getters y Setters
    public Integer getIdPresentacionCurso() {
        return idPresentacionCurso;
    }

    public void setIdPresentacionCurso(Integer idPresentacionCurso) {
        this.idPresentacionCurso = idPresentacionCurso;
    }

    public Guion getGuion() {
        return guion;
    }

    public void setGuion(Guion guion) {
        this.guion = guion;
    }

    public List<Diapositiva> getDiapositivas() {
        return diapositivas;
    }

    public void setDiapositivas(List<Diapositiva> diapositivas) {
        this.diapositivas = diapositivas;
    }
}
