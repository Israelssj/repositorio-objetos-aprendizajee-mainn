package com.itst.repositorio_objetos_aprendizaje.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "elementoseleccionmultiple")
public class ElementosEleccionMultiple {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idElementosEleccionMultiple;

    @Column(name = "pregunta", columnDefinition = "TEXT")
    private String pregunta;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idEleccionMultiple")
    private EleccionMultiple eleccionMultiple;

    @JsonManagedReference
    @OneToMany(mappedBy = "elementosEleccionMultiple", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<RespuestaEleccionMultiple> respuestas;

    // Constructores
    public ElementosEleccionMultiple() {
    }

    public ElementosEleccionMultiple(String pregunta, EleccionMultiple eleccionMultiple) {
        this.pregunta = pregunta;
        this.eleccionMultiple = eleccionMultiple;
    }

    // Getters y Setters
    public Integer getIdElementosEleccionMultiple() {
        return idElementosEleccionMultiple;
    }

    public void setIdElementosEleccionMultiple(Integer idElementosEleccionMultiple) {
        this.idElementosEleccionMultiple = idElementosEleccionMultiple;
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public EleccionMultiple getEleccionMultiple() {
        return eleccionMultiple;
    }

    public void setEleccionMultiple(EleccionMultiple eleccionMultiple) {
        this.eleccionMultiple = eleccionMultiple;
    }

    public List<RespuestaEleccionMultiple> getRespuestas() {
        return respuestas;
    }

    public void setRespuestas(List<RespuestaEleccionMultiple> respuestas) {
        this.respuestas = respuestas;
    }
}
