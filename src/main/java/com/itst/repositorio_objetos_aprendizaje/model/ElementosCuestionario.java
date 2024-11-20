package com.itst.repositorio_objetos_aprendizaje.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "elementoscuestionario")
public class ElementosCuestionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idElementosCuestionario;

    @Column(name = "pregunta", columnDefinition = "TEXT", nullable = false)
    private String pregunta;

    @Column(name = "tipo_pregunta", nullable = false)
    private Integer tipoPregunta;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cuestionario")
    @JsonBackReference
    private Cuestionario cuestionario;

    @OneToMany(mappedBy = "elementosCuestionario", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<RespuestaCuestionario> respuestasCuestionario;

    // Getters y Setters
    public Integer getIdElementosCuestionario() { return idElementosCuestionario; }
    public void setIdElementosCuestionario(Integer idElementosCuestionario) { this.idElementosCuestionario = idElementosCuestionario; }
    public String getPregunta() { return pregunta; }
    public void setPregunta(String pregunta) { this.pregunta = pregunta; }
    public Integer getTipoPregunta() { return tipoPregunta; }
    public void setTipoPregunta(Integer tipoPregunta) { this.tipoPregunta = tipoPregunta; }
    public Cuestionario getCuestionario() { return cuestionario; }
    public void setCuestionario(Cuestionario cuestionario) { this.cuestionario = cuestionario; }
    public List<RespuestaCuestionario> getRespuestasCuestionario() { return respuestasCuestionario; }
    public void setRespuestasCuestionario(List<RespuestaCuestionario> respuestasCuestionario) { this.respuestasCuestionario = respuestasCuestionario; }
}
