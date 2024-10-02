package com.itst.repositorio_objetos_aprendizaje.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "elementoscuestionario")
public class ElementosCuestionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idElementosCuestionario;

    @Column(name = "pregunta", columnDefinition = "TEXT")
    private String pregunta;

    @Column(name = "tipoPregunta")
    private Integer tipoPregunta;

    @ManyToOne
    @JoinColumn(name = "idCuestionario")
    private Cuestionario cuestionario;

    @OneToMany(mappedBy = "elementosCuestionario", cascade = CascadeType.ALL)
    private List<RespuestaCuestionario> respuestasCuestionario;

    // Constructores
    public ElementosCuestionario() {
        // Constructor vacío necesario para JPA
    }

    public ElementosCuestionario(String pregunta, Integer tipoPregunta, Cuestionario cuestionario) {
        this.pregunta = pregunta;
        this.tipoPregunta = tipoPregunta;
        this.cuestionario = cuestionario;
    }

    // Getters y Setters
    public Integer getIdElementosCuestionario() {
        return idElementosCuestionario;
    }

    public void setIdElementosCuestionario(Integer idElementosCuestionario) {
        this.idElementosCuestionario = idElementosCuestionario;
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public Integer getTipoPregunta() {
        return tipoPregunta;
    }

    public void setTipoPregunta(Integer tipoPregunta) {
        this.tipoPregunta = tipoPregunta;
    }

    public Cuestionario getCuestionario() {
        return cuestionario;
    }

    public void setCuestionario(Cuestionario cuestionario) {
        this.cuestionario = cuestionario;
    }

    public List<RespuestaCuestionario> getRespuestasCuestionario() {
        return respuestasCuestionario;
    }

    public void setRespuestasCuestionario(List<RespuestaCuestionario> respuestasCuestionario) {
        this.respuestasCuestionario = respuestasCuestionario;
    }
}
