package com.itst.repositorio_objetos_aprendizaje.model;

import jakarta.persistence.*;

@Entity
@Table(name = "respuestacuestionario")
public class RespuestaCuestionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idRespuestaCuestionario;

    @Column(name = "texto", columnDefinition = "TEXT")
    private String texto;

    @ManyToOne
    @JoinColumn(name = "idElementosCuestionario")
    private ElementosCuestionario elementosCuestionario;

    // Constructores
    public RespuestaCuestionario() {
        // Constructor vacío necesario para JPA
    }

    public RespuestaCuestionario(String texto, ElementosCuestionario elementosCuestionario) {
        this.texto = texto;
        this.elementosCuestionario = elementosCuestionario;
    }


    // Getters y Setters
    public Integer getIdRespuestaCuestionario() {
        return idRespuestaCuestionario;
    }

    public void setIdRespuestaCuestionario(Integer idRespuestaCuestionario) {
        this.idRespuestaCuestionario = idRespuestaCuestionario;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public ElementosCuestionario getElementosCuestionario() {
        return elementosCuestionario;
    }

    public void setElementosCuestionario(ElementosCuestionario elementosCuestionario) {
        this.elementosCuestionario = elementosCuestionario;
    }
}
