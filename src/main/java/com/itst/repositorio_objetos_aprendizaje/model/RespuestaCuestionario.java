package com.itst.repositorio_objetos_aprendizaje.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "respuestacuestionario")
public class RespuestaCuestionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idRespuestaCuestionario;

    @Column(name = "texto", columnDefinition = "TEXT", nullable = false)
    private String texto;

    @Column(name = "correcta", nullable = false)
    private Boolean correcta;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_elementos_cuestionario")
    @JsonBackReference
    private ElementosCuestionario elementosCuestionario;

    // Getters y Setters
    public Integer getIdRespuestaCuestionario() { return idRespuestaCuestionario; }
    public void setIdRespuestaCuestionario(Integer idRespuestaCuestionario) { this.idRespuestaCuestionario = idRespuestaCuestionario; }
    public String getTexto() { return texto; }
    public void setTexto(String texto) { this.texto = texto; }
    public Boolean getCorrecta() { return correcta; }
    public void setCorrecta(Boolean correcta) { this.correcta = correcta; }
    public ElementosCuestionario getElementosCuestionario() { return elementosCuestionario; }
    public void setElementosCuestionario(ElementosCuestionario elementosCuestionario) { this.elementosCuestionario = elementosCuestionario; }
}
