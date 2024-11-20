package com.itst.repositorio_objetos_aprendizaje.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "cuestionario")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idCuestionario")
public class Cuestionario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCuestionario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_guion")
    private Guion guion;

    @OneToMany(mappedBy = "cuestionario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ElementosCuestionario> elementosCuestionario;

    // Getters y Setters

    public List<ElementosCuestionario> getElementosCuestionario() {
        return elementosCuestionario;
    }

    public void setElementosCuestionario(List<ElementosCuestionario> elementosCuestionario) {
        this.elementosCuestionario = elementosCuestionario;
    }

    public Guion getGuion() {
        return guion;
    }

    public void setGuion(Guion guion) {
        this.guion = guion;
    }

    public Integer getIdCuestionario() {
        return idCuestionario;
    }

    public void setIdCuestionario(Integer idCuestionario) {
        this.idCuestionario = idCuestionario;
    }
}
