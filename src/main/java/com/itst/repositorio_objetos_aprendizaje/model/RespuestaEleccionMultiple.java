package com.itst.repositorio_objetos_aprendizaje.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "respuestaeleccionmultiple")
public class RespuestaEleccionMultiple {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idRespuestaEleccionMultiple;

    @Column(name = "textoRespuesta", columnDefinition = "TEXT")
    private String textoRespuesta;

    @Column(name = "correcta")
    private Boolean correcta;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idElementosEleccionMultiple")
    private ElementosEleccionMultiple elementosEleccionMultiple;

    // Constructores
    public RespuestaEleccionMultiple() {
    }

    public RespuestaEleccionMultiple(String textoRespuesta, Boolean correcta, ElementosEleccionMultiple elementosEleccionMultiple) {
        this.textoRespuesta = textoRespuesta;
        this.correcta = correcta;
        this.elementosEleccionMultiple = elementosEleccionMultiple;
    }

    // Getters y Setters
    public Integer getIdRespuestaEleccionMultiple() {
        return idRespuestaEleccionMultiple;
    }

    public void setIdRespuestaEleccionMultiple(Integer idRespuestaEleccionMultiple) {
        this.idRespuestaEleccionMultiple = idRespuestaEleccionMultiple;
    }

    public String getTextoRespuesta() {
        return textoRespuesta;
    }

    public void setTextoRespuesta(String textoRespuesta) {
        this.textoRespuesta = textoRespuesta;
    }

    public Boolean getCorrecta() {
        return correcta;
    }

    public void setCorrecta(Boolean correcta) {
        this.correcta = correcta;
    }

    public ElementosEleccionMultiple getElementosEleccionMultiple() {
        return elementosEleccionMultiple;
    }

    public void setElementosEleccionMultiple(ElementosEleccionMultiple elementosEleccionMultiple) {
        this.elementosEleccionMultiple = elementosEleccionMultiple;
    }
}
