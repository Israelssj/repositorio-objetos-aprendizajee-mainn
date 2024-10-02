package com.itst.repositorio_objetos_aprendizaje.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "cuestionario")
public class Cuestionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCuestionario;

    @ManyToOne
    @JoinColumn(name = "idGuion")
    private Guion guion;

    @OneToMany(mappedBy = "cuestionario", cascade = CascadeType.ALL)
    private List<ElementosCuestionario> elementosCuestionario;


    public Cuestionario() {
    }


    public Cuestionario(Guion guion) {
        this.guion = guion;
    }


    public Integer getIdCuestionario() {
        return idCuestionario;
    }

    public void setIdCuestionario(Integer idCuestionario) {
        this.idCuestionario = idCuestionario;
    }

    public Guion getGuion() {
        return guion;
    }

    public void setGuion(Guion guion) {
        this.guion = guion;
    }

    public List<ElementosCuestionario> getElementosCuestionario() {
        return elementosCuestionario;
    }

    public void setElementosCuestionario(List<ElementosCuestionario> elementosCuestionario) {
        this.elementosCuestionario = elementosCuestionario;
    }
}
