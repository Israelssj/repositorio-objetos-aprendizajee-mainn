package com.itst.repositorio_objetos_aprendizaje.controller;

import com.itst.repositorio_objetos_aprendizaje.model.Rol;
import com.itst.repositorio_objetos_aprendizaje.repository.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rol")
@CrossOrigin(origins = "http://localhost:3000") // Configura el CORS
public class RolController {

    @Autowired
    private RolRepository rolRepository;

    // Obtener todos los roles
    @GetMapping
    public ResponseEntity<List<Rol>> findAll() {
        List<Rol> roles = rolRepository.findAll();
        return ResponseEntity.ok(roles);
    }

    // Obtener roles para el registro
    @GetMapping("/registro")
    public ResponseEntity<List<Rol>> obtenerRolesParaRegistro() {
        List<Rol> roles = rolRepository.findAll();
        return ResponseEntity.ok(roles);
    }

    // Crear un nuevo rol
    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<Rol> create(@RequestBody Rol newRol) {
        if (newRol.getNombreRol() == null || newRol.getDescripcion() == null) {
            return ResponseEntity.badRequest().build();
        }
        Rol savedRol = rolRepository.save(newRol);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedRol);
    }

    // Actualizar un rol existente
    @PutMapping(value = "/{idRol}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Rol> update(@PathVariable Integer idRol, @RequestBody Rol updatedRol) {
        return rolRepository.findById(idRol)
                .map(existingRol -> {
                    updatedRol.setIdRol(existingRol.getIdRol()); // Asegúrate de usar el ID correcto
                    Rol savedRol = rolRepository.save(updatedRol);
                    return ResponseEntity.ok(savedRol);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // Eliminar un rol
    @DeleteMapping("/{idRol}")
    public ResponseEntity<Void> delete(@PathVariable Integer idRol) {
        if (rolRepository.existsById(idRol)) {
            rolRepository.deleteById(idRol);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
