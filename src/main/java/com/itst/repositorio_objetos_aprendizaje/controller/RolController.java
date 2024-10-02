package com.itst.repositorio_objetos_aprendizaje.controller;

import com.itst.repositorio_objetos_aprendizaje.model.Rol;
import com.itst.repositorio_objetos_aprendizaje.repository.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/rol")
@CrossOrigin(origins = "http://localhost:3000")
public class RolController {

    @Autowired
    private RolRepository rolRepository;

    @GetMapping
    public ResponseEntity<Iterable<Rol>> findAll() {
        return ResponseEntity.ok(rolRepository.findAll());
    }


    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<Rol> create(@RequestBody Rol newRol) {
        Rol savedRol = rolRepository.save(newRol);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedRol);
    }

    
    @PutMapping(value = "/{idRol}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Void> update(@PathVariable Integer idRol, @RequestBody Rol updatedRol) {
        Optional<Rol> existingRol = rolRepository.findById(idRol);
        if (existingRol.isPresent()) {
            updatedRol.setIdRol(existingRol.get().getIdRol());
            rolRepository.save(updatedRol);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{idRol}")
    public ResponseEntity<Void> delete(@PathVariable Integer idRol) {
        if (rolRepository.existsById(idRol)) {
            rolRepository.deleteById(idRol);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
