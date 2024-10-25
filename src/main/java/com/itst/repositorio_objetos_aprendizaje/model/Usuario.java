package com.itst.repositorio_objetos_aprendizaje.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")  // Cambiado para que coincida con la base de datos
    private Integer idUsuario;

    @Column(name = "nombre", nullable = false, length = 50)
    private String nombreUsuario;

    @Column(name = "ap_paterno", nullable = false, length = 50)  // Cambiado para que coincida con la base de datos
    private String apellidoPaterno;

    @Column(name = "ap_materno", nullable = false, length = 50)  // Cambiado para que coincida con la base de datos
    private String apellidoMaterno;

    @Column(name = "email", nullable = false, length = 100, unique = true)
    private String email;

    @Column(name = "password", nullable = false, length = 255)
    private String password;

    @ManyToOne
    @JoinColumn(name = "id_rol", nullable = false)  // Cambiado para que coincida con la base de datos
    @JsonBackReference
    private Rol rol;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Descarga> descargas;

    // Constructor vacío
    public Usuario() {}

    // Constructor con parámetros
    public Usuario(String nombreUsuario, String apellidoPaterno, String apellidoMaterno, String email, String password, Rol rol) {
        this.nombreUsuario = nombreUsuario;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.email = email;
        this.password = password;
        this.rol = rol;
    }

    // Getters y Setters
    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public List<Descarga> getDescargas() {
        return descargas;
    }

    public void setDescargas(List<Descarga> descargas) {
        this.descargas = descargas;
    }
}
