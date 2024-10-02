package com.itst.repositorio_objetos_aprendizaje.controller;

import com.itst.repositorio_objetos_aprendizaje.model.Evaluacion;
import com.itst.repositorio_objetos_aprendizaje.repository.EvaluacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/evaluaciones")
@CrossOrigin(origins = "http://localhost:3000")
public class EvaluacionController {

    private final EvaluacionRepository evaluacionRepository;

    @Autowired
    public EvaluacionController(EvaluacionRepository evaluacionRepository) {
        this.evaluacionRepository = evaluacionRepository;
    }

    @GetMapping("/")
    public List<Evaluacion> getAllEvaluaciones() {
        return evaluacionRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Evaluacion> getEvaluacionById(@PathVariable Integer id) {
        Optional<Evaluacion> evaluacion = evaluacionRepository.findById(id);
        return evaluacion.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/")
    public ResponseEntity<Evaluacion> createEvaluacion(@RequestBody Evaluacion evaluacion) {
        Evaluacion savedEvaluacion = evaluacionRepository.save(evaluacion);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedEvaluacion);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Evaluacion> updateEvaluacion(@PathVariable Integer id, @RequestBody Evaluacion evaluacionDetails) {
        Optional<Evaluacion> optionalEvaluacion = evaluacionRepository.findById(id);
        if (optionalEvaluacion.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Evaluacion evaluacion = optionalEvaluacion.get();
        evaluacion.setNota(evaluacionDetails.getNota());
        evaluacion.setComentario(evaluacionDetails.getComentario());
        Evaluacion updatedEvaluacion = evaluacionRepository.save(evaluacion);
        return ResponseEntity.ok(updatedEvaluacion);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvaluacion(@PathVariable Integer id) {
        Optional<Evaluacion> evaluacion = evaluacionRepository.findById(id);
        if (evaluacion.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        evaluacionRepository.delete(evaluacion.get());
        return ResponseEntity.noContent().build();
    }
}
