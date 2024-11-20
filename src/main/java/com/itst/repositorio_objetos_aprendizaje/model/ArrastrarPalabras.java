package com.itst.repositorio_objetos_aprendizaje.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "arrastarpalabras")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idArrastarPalabras")
public class ArrastrarPalabras {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idArrastarPalabras;

    @Column(name = "textoBase", columnDefinition = "TEXT")
    private String textoBase;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idGuion")
    private Guion guion;

    @OneToMany(mappedBy = "arrastrarPalabras", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ElementosArrastrarPalabras> elementosArrastrarPalabras;

    // Getters y Setters

    public String getTextoBase() {
        return textoBase;
    }

    public void setTextoBase(String textoBase) {
        this.textoBase = textoBase;
    }

    public Integer getIdArrastarPalabras() {
        return idArrastarPalabras;
    }

    public void setIdArrastarPalabras(Integer idArrastarPalabras) {
        this.idArrastarPalabras = idArrastarPalabras;
    }

    public Guion getGuion() {
        return guion;
    }

    public void setGuion(Guion guion) {
        this.guion = guion;
    }

    public List<ElementosArrastrarPalabras> getElementosArrastrarPalabras() {
        return elementosArrastrarPalabras;
    }

    public void setElementosArrastrarPalabras(List<ElementosArrastrarPalabras> elementosArrastrarPalabras) {
        this.elementosArrastrarPalabras = elementosArrastrarPalabras;
    }
}
