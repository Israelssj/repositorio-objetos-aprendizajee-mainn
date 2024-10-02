package com.itst.repositorio_objetos_aprendizaje.controller;

import com.itst.repositorio_objetos_aprendizaje.model.Usuario;
import com.itst.repositorio_objetos_aprendizaje.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/usuario")
@CrossOrigin(origins = "http://localhost:3000")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping
    public ResponseEntity<Iterable<Usuario>> findAll() {
        return ResponseEntity.ok(usuarioRepository.findAll());
    }

    @GetMapping("/{idUsuario}")
    public ResponseEntity<Usuario> findById(@PathVariable Integer idUsuario) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(idUsuario);
        return usuarioOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<Usuario> create(@RequestBody Usuario newUsuario) {
        if (newUsuario.getRol() == null || newUsuario.getRol().getIdRol() == null) {
            return ResponseEntity.badRequest().body(null);
        }


        if (newUsuario.getEmail() == null || newUsuario.getPassword() == null) {
            return ResponseEntity.badRequest().body(null);
        }

        Usuario savedUsuario = usuarioRepository.save(newUsuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUsuario);
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
