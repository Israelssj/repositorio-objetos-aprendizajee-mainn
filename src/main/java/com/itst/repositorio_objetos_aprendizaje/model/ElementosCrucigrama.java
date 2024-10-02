package com.itst.repositorio_objetos_aprendizaje.model;

import jakarta.persistence.*;

@Entity
@Table(name = "elementoscrucigrama")
public class ElementosCrucigrama {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idElementosCrucigrama;

    @Column(name = "pregunta", length = 150)
    private String pregunta;

    @Column(name = "pista", length = 150)
    private String pista;

    @ManyToOne
    @JoinColumn(name = "idCrucigrama")
    private Crucigrama crucigrama;

    // Constructores
    public ElementosCrucigrama() {
    }

    public ElementosCrucigrama(String pregunta, String pista, Crucigrama crucigrama) {
        this.pregunta = pregunta;
        this.pista = pista;
        this.crucigrama = crucigrama;
    }

    // Getters y Setters
    public Integer getIdElementosCrucigrama() {
        return idElementosCrucigrama;
    }

    public void setIdElementosCrucigrama(Integer idElementosCrucigrama) {
        this.idElementosCrucigrama = idElementosCrucigrama;
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public String getPista() {
        return pista;
    }

    public void setPista(String pista) {
        this.pista = pista;
    }

    public Crucigrama getCrucigrama() {
        return crucigrama;
    }

    public void setCrucigrama(Crucigrama crucigrama) {
        this.crucigrama = crucigrama;
    }
}
