package com.itst.repositorio_objetos_aprendizaje.controller;

import com.itst.repositorio_objetos_aprendizaje.model.Usuario;
import com.itst.repositorio_objetos_aprendizaje.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@RequestMapping("/usuario")
@CrossOrigin(origins = "http://localhost:3000")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping
    public ResponseEntity<?> findAll() {
        try {
            return ResponseEntity.ok(usuarioRepository.findAll());
        } catch (Exception e) {
            System.err.println("Error en findAll: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al obtener los usuarios: " + e.getMessage());
        }
    }


    @GetMapping("/{idUsuario}")
    public ResponseEntity<Usuario> findById(@PathVariable Integer idUsuario) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(idUsuario);
        return usuarioOptional.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<Usuario> create(@RequestBody Usuario newUsuario) {
        if (newUsuario.getRol() == null || newUsuario.getRol().getIdRol() == null) {
            return ResponseEntity.badRequest().body(null);
        }

        if (newUsuario.getEmail() == null || newUsuario.getPassword() == null) {
            return ResponseEntity.badRequest().body(null);
        }

        try {
            Usuario savedUsuario = usuarioRepository.save(newUsuario);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedUsuario);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al crear el usuario", e);
        }
    }

    @PutMapping(value = "/{idUsuario}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Usuario> update(@PathVariable Integer idUsuario, @RequestBody Usuario updatedUsuario) {
        Optional<Usuario> existingUsuario = usuarioRepository.findById(idUsuario);
        if (existingUsuario.isPresent()) {
            updatedUsuario.setIdUsuario(existingUsuario.get().getIdUsuario());
            Usuario savedUsuario = usuarioRepository.save(updatedUsuario);
            return ResponseEntity.ok(savedUsuario);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{idUsuario}")
    public ResponseEntity<Void> delete(@PathVariable Integer idUsuario) {
        if (usuarioRepository.existsById(idUsuario)) {
            usuarioRepository.deleteById(idUsuario);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
