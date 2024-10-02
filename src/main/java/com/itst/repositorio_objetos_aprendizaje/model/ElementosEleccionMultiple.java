package com.itst.repositorio_objetos_aprendizaje.model;

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

    @ManyToOne
    @JoinColumn(name = "idEleccionMultiple")
    private EleccionMultiple eleccionMultiple;

    @OneToMany(mappedBy = "elementosEleccionMultiple", cascade = CascadeType.ALL)
    private List<RespuestaEleccionMultiple> respuestas;

    // Constructores
    public ElementosEleccionMultiple() {
        // Constructor vacío necesario para JPA
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
