package com.itst.repositorio_objetos_aprendizaje.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "guion")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idGuion")
public class Guion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_guion")
    private Integer idGuion;

    @Column(name = "titulo", length = 50)
    private String titulo;

    @Column(name = "descripcion", length = 50)
    private String descripcion;

    @Column(name = "fecha_creacion", nullable = false)
    private String fechaCreacion;

    @Column(name = "materia", length = 50)
    private String materia;

    @Column(name = "nombre_docente", length = 100)
    private String nombreDocente;

    @Column(name = "nombre_materia", length = 100)
    private String nombreMateria;

    @Column(name = "nombre_tema", length = 100)
    private String nombreTema;

    @Column(name = "nombre_subtema", length = 100)
    private String nombreSubtema;

    @Column(name = "semestre", length = 20)
    private String semestre;

    @Column(name = "tipo_objeto", length = 50)
    private String tipoObjeto;

    @Column(name = "objeto_aprendizaje", columnDefinition = "TEXT")
    private String objetoAprendizaje;

    @Column(name = "estado", nullable = false, length = 20)
    private String estado = "pendiente";

    @Column(name = "observacion", columnDefinition = "TEXT")
    private String observacion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @OneToMany(mappedBy = "guion", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<VideoInteractivo> videosInteractivos;

    @OneToMany(mappedBy = "guion", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Crucigrama> crucigramas;

    @OneToMany(mappedBy = "guion", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Cuestionario> cuestionarios;

    @OneToMany(mappedBy = "guion", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ArrastrarPalabras> arrastrarPalabras;

    @OneToMany(mappedBy = "guion", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<EleccionMultiple> eleccionesMultiples;

    @OneToMany(mappedBy = "guion", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<PresentacionCurso> presentacionesCurso;

    // Getters y Setters
    public Integer getIdGuion() {
        return idGuion;
    }

    public void setIdGuion(Integer idGuion) {
        this.idGuion = idGuion;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getMateria() {
        return materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }

    public String getNombreDocente() {
        return nombreDocente;
    }

    public void setNombreDocente(String nombreDocente) {
        this.nombreDocente = nombreDocente;
    }

    public String getNombreMateria() {
        return nombreMateria;
    }

    public void setNombreMateria(String nombreMateria) {
        this.nombreMateria = nombreMateria;
    }

    public String getNombreTema() {
        return nombreTema;
    }

    public void setNombreTema(String nombreTema) {
        this.nombreTema = nombreTema;
    }

    public String getNombreSubtema() {
        return nombreSubtema;
    }

    public void setNombreSubtema(String nombreSubtema) {
        this.nombreSubtema = nombreSubtema;
    }

    public String getSemestre() {
        return semestre;
    }

    public void setSemestre(String semestre) {
        this.semestre = semestre;
    }

    public String getTipoObjeto() {
        return tipoObjeto;
    }

    public void setTipoObjeto(String tipoObjeto) {
        this.tipoObjeto = tipoObjeto;
    }

    public String getObjetoAprendizaje() {
        return objetoAprendizaje;
    }

    public void setObjetoAprendizaje(String objetoAprendizaje) {
        this.objetoAprendizaje = objetoAprendizaje;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<VideoInteractivo> getVideosInteractivos() {
        return videosInteractivos;
    }

    public void setVideosInteractivos(List<VideoInteractivo> videosInteractivos) {
        this.videosInteractivos = videosInteractivos;
    }

    public List<Crucigrama> getCrucigramas() {
        return crucigramas;
    }

    public void setCrucigramas(List<Crucigrama> crucigramas) {
        this.crucigramas = crucigramas;
    }

    public List<Cuestionario> getCuestionarios() {
        return cuestionarios;
    }

    public void setCuestionarios(List<Cuestionario> cuestionarios) {
        this.cuestionarios = cuestionarios;
    }

    public List<ArrastrarPalabras> getArrastrarPalabras() {
        return arrastrarPalabras;
    }

    public void setArrastrarPalabras(List<ArrastrarPalabras> arrastrarPalabras) {
        this.arrastrarPalabras = arrastrarPalabras;
    }

    public List<EleccionMultiple> getEleccionesMultiples() {
        return eleccionesMultiples;
    }

    public void setEleccionesMultiples(List<EleccionMultiple> eleccionesMultiples) {
        this.eleccionesMultiples = eleccionesMultiples;
    }

    public List<PresentacionCurso> getPresentacionesCurso() {
        return presentacionesCurso;
    }

    public void setPresentacionesCurso(List<PresentacionCurso> presentacionesCurso) {
        this.presentacionesCurso = presentacionesCurso;
    }
}