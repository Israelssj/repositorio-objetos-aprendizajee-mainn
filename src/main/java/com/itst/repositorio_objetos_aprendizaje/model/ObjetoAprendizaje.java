package com.itst.repositorio_objetos_aprendizaje.model;

import jakarta.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "objetoaprendizaje")
public class ObjetoAprendizaje {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idObjetoAprendizaje;

    @Column(name = "archivo", length = 255)
    private String archivo;

    @Column(name = "fecha", nullable = false)
    private Date fecha;

    @ManyToOne
    @JoinColumn(name = "idGuion")
    private Guion guion;

    @OneToMany(mappedBy = "objetoAprendizaje", cascade = CascadeType.ALL)
    private List<Descarga> descargas;

    @OneToOne
    @JoinColumn(name = "idEvaluacion")
    private Evaluacion evaluacion;

    public ObjetoAprendizaje() {
        // Constructor vacío necesario para JPA
    }

    public ObjetoAprendizaje(String archivo, Date fecha) {
        this.archivo = archivo;
        this.fecha = fecha;
    }

    public Integer getIdObjetoAprendizaje() {
        return idObjetoAprendizaje;
    }

    public void setIdObjetoAprendizaje(Integer idObjetoAprendizaje) {
        this.idObjetoAprendizaje = idObjetoAprendizaje;
    }

    public String getArchivo() {
        return archivo;
    }

    public void setArchivo(String archivo) {
        this.archivo = archivo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Guion getGuion() {
        return guion;
    }

    public void setGuion(Guion guion) {
        this.guion = guion;
    }

    public List<Descarga> getDescargas() {
        return descargas;
    }

    public void setDescargas(List<Descarga> descargas) {
        this.descargas = descargas;
    }

    public Evaluacion getEvaluacion() {
        return evaluacion;
    }

    public void setEvaluacion(Evaluacion evaluacion) {
        this.evaluacion = evaluacion;
    }
}
