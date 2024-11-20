package com.itst.repositorio_objetos_aprendizaje.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "eleccionmultiple")
public class EleccionMultiple {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEleccionMultiple;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idGuion")
    private Guion guion;

    @JsonManagedReference
    @OneToMany(mappedBy = "eleccionMultiple", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ElementosEleccionMultiple> elementosEleccionMultiple;

    // Getters y Setters
    public Integer getIdEleccionMultiple() {
        return idEleccionMultiple;
    }

    public void setIdEleccionMultiple(Integer idEleccionMultiple) {
        this.idEleccionMultiple = idEleccionMultiple;
    }

    public List<ElementosEleccionMultiple> getElementosEleccionMultiple() {
        return elementosEleccionMultiple;
    }

    public void setElementosEleccionMultiple(List<ElementosEleccionMultiple> elementosEleccionMultiple) {
        this.elementosEleccionMultiple = elementosEleccionMultiple;
    }

    public Guion getGuion() {
        return guion;
    }

    public void setGuion(Guion guion) {
        this.guion = guion;
    }
}
