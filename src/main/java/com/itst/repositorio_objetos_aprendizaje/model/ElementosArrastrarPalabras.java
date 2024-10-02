package com.itst.repositorio_objetos_aprendizaje.model;

import jakarta.persistence.*;

@Entity
@Table(name = "elementosarrastarpalabras")
public class ElementosArrastrarPalabras {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idElementosArrastrarPalabras;

    @Column(name = "palabras", columnDefinition = "TEXT")
    private String palabras;

    @ManyToOne
    @JoinColumn(name = "idArrastarPalabras")
    private ArrastrarPalabras arrastrarPalabras;

    // Constructores
    public ElementosArrastrarPalabras() {
        // Constructor vacío necesario para JPA
    }

    public ElementosArrastrarPalabras(String palabras, ArrastrarPalabras arrastrarPalabras) {
        this.palabras = palabras;
        this.arrastrarPalabras = arrastrarPalabras;
    }

    // Getters y Setters
    public Integer getIdElementosArrastrarPalabras() {
        return idElementosArrastrarPalabras;
    }

    public void setIdElementosArrastrarPalabras(Integer idElementosArrastrarPalabras) {
        this.idElementosArrastrarPalabras = idElementosArrastrarPalabras;
    }

    public String getPalabras() {
        return palabras;
    }

    public void setPalabras(String palabras) {
        this.palabras = palabras;
    }

    public ArrastrarPalabras getArrastrarPalabras() {
        return arrastrarPalabras;
    }

    public void setArrastrarPalabras(ArrastrarPalabras arrastrarPalabras) {
        this.arrastrarPalabras = arrastrarPalabras;
    }
}
