package com.itst.repositorio_objetos_aprendizaje.controller;

import com.itst.repositorio_objetos_aprendizaje.model.Cuestionario;
import com.itst.repositorio_objetos_aprendizaje.repository.CuestionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cuestionarios")
public class CuestionarioController {

    private final CuestionarioRepository cuestionarioRepository;

    @Autowired
    public CuestionarioController(CuestionarioRepository cuestionarioRepository) {
        this.cuestionarioRepository = cuestionarioRepository;
    }

    @GetMapping
    public ResponseEntity<List<Cuestionario>> getAllCuestionarios() {
        List<Cuestionario> cuestionarios = cuestionarioRepository.findAll();
        return new ResponseEntity<>(cuestionarios, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cuestionario> getCuestionarioById(@PathVariable Integer id) {
        Optional<Cuestionario> cuestionarioOptional = cuestionarioRepository.findById(id);
        return cuestionarioOptional.map(cuestionario -> new ResponseEntity<>(cuestionario, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Cuestionario> createCuestionario(@RequestBody Cuestionario cuestionario) {
        Cuestionario nuevoCuestionario = cuestionarioRepository.save(cuestionario);
        return new ResponseEntity<>(nuevoCuestionario, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cuestionario> updateCuestionario(@PathVariable Integer id, @RequestBody Cuestionario cuestionarioActualizado) {
        Optional<Cuestionario> cuestionarioOptional = cuestionarioRepository.findById(id);
        if (cuestionarioOptional.isPresent()) {
            Cuestionario cuestionarioExistente = cuestionarioOptional.get();
            cuestionarioExistente.setGuion(cuestionarioActualizado.getGuion());
            Cuestionario cuestionarioActualizadoDB = cuestionarioRepository.save(cuestionarioExistente);
            return new ResponseEntity<>(cuestionarioActualizadoDB, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCuestionario(@PathVariable Integer id) {
        Optional<Cuestionario> cuestionarioOptional = cuestionarioRepository.findById(id);
        if (cuestionarioOptional.isPresent()) {
            cuestionarioRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
