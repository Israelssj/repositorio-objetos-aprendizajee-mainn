package com.itst.repositorio_objetos_aprendizaje.model;

import jakarta.persistence.*;

@Entity
@Table(name = "descarga")
public class Descarga {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idDescarga;

    @ManyToOne
    @JoinColumn(name = "idUsuario")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "idObjetoAprendizaje")
    private ObjetoAprendizaje objetoAprendizaje;

    @Column(name = "fecha", nullable = false)
    private java.sql.Date fecha;

    // Constructores, getters y setters
    public Descarga() {
    }

    public Descarga(Usuario usuario, ObjetoAprendizaje objetoAprendizaje, java.sql.Date fecha) {
        this.usuario = usuario;
        this.objetoAprendizaje = objetoAprendizaje;
        this.fecha = fecha;
    }

    public Integer getIdDescarga() {
        return idDescarga;
    }

    public void setIdDescarga(Integer idDescarga) {
        this.idDescarga = idDescarga;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public ObjetoAprendizaje getObjetoAprendizaje() {
        return objetoAprendizaje;
    }

    public void setObjetoAprendizaje(ObjetoAprendizaje objetoAprendizaje) {
        this.objetoAprendizaje = objetoAprendizaje;
    }

    public java.sql.Date getFecha() {
        return fecha;
    }

    public void setFecha(java.sql.Date fecha) {
        this.fecha = fecha;
    }
}
