package com.itst.repositorio_objetos_aprendizaje.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "arrastarpalabras")
public class ArrastrarPalabras {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idArrastarPalabras;

    @Column(name = "textoBase", columnDefinition = "TEXT")
    private String textoBase;

    @ManyToOne
    @JoinColumn(name = "idGuion")
    private Guion guion;

    @OneToMany(mappedBy = "arrastrarPalabras", cascade = CascadeType.ALL)
    private List<ElementosArrastrarPalabras> elementosArrastrarPalabras;


    public ArrastrarPalabras() {

    }

    public ArrastrarPalabras(String textoBase, Guion guion) {
        this.textoBase = textoBase;
        this.guion = guion;
    }

    // Getters y Setters
    public Integer getIdArrastarPalabras() {
        return idArrastarPalabras;
    }

    public void setIdArrastarPalabras(Integer idArrastarPalabras) {
        this.idArrastarPalabras = idArrastarPalabras;
    }

    public String getTextoBase() {
        return textoBase;
    }

    public void setTextoBase(String textoBase) {
        this.textoBase = textoBase;
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
