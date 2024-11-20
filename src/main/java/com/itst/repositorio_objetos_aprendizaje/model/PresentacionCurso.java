package com.itst.repositorio_objetos_aprendizaje.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "presentacioncurso")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idPresentacionCurso")
public class PresentacionCurso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPresentacionCurso;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idGuion")
    private Guion guion;

    @OneToMany(mappedBy = "presentacionCurso", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Diapositiva> diapositivas;

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
