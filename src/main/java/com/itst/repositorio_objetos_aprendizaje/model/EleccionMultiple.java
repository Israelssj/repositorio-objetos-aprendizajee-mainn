package com.itst.repositorio_objetos_aprendizaje.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "eleccionmultiple")
public class EleccionMultiple {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEleccionMultiple;

    @ManyToOne
    @JoinColumn(name = "idGuion")
    private Guion guion;

    @OneToMany(mappedBy = "eleccionMultiple", cascade = CascadeType.ALL)
    private List<ElementosEleccionMultiple> elementosEleccionMultiple;

    // Constructores
    public EleccionMultiple() {
        // Constructor vacío necesario para JPA
    }

    public EleccionMultiple(Guion guion) {
        this.guion = guion;
    }

    // Getters y Setters
    public Integer getIdEleccionMultiple() {
        return idEleccionMultiple;
    }

    public void setIdEleccionMultiple(Integer idEleccionMultiple) {
        this.idEleccionMultiple = idEleccionMultiple;
    }

    public Guion getGuion() {
        return guion;
    }

    public void setGuion(Guion guion) {
        this.guion = guion;
    }

    public List<ElementosEleccionMultiple> getElementosEleccionMultiple() {
        return elementosEleccionMultiple;
    }

    public void setElementosEleccionMultiple(List<ElementosEleccionMultiple> elementosEleccionMultiple) {
        this.elementosEleccionMultiple = elementosEleccionMultiple;
    }
}
