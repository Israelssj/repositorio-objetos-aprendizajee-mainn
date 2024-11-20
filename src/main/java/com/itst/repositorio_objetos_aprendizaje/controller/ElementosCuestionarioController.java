package com.itst.repositorio_objetos_aprendizaje.controller;

import com.itst.repositorio_objetos_aprendizaje.model.ElementosCuestionario;
import com.itst.repositorio_objetos_aprendizaje.repository.ElementosCuestionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/elementos-cuestionario")
public class ElementosCuestionarioController {

    private final ElementosCuestionarioRepository elementosCuestionarioRepository;

    @Autowired
    public ElementosCuestionarioController(ElementosCuestionarioRepository elementosCuestionarioRepository) {
        this.elementosCuestionarioRepository = elementosCuestionarioRepository;
    }

    @GetMapping
    public ResponseEntity<List<ElementosCuestionario>> getAllElementosCuestionario() {
        List<ElementosCuestionario> elementosCuestionario = elementosCuestionarioRepository.findAll();
        return ResponseEntity.ok(elementosCuestionario);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ElementosCuestionario> getElementosCuestionarioById(@PathVariable Integer id) {
        return elementosCuestionarioRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ElementosCuestionario> createElementosCuestionario(@RequestBody ElementosCuestionario elementosCuestionario) {
        if (elementosCuestionario.getPregunta() == null || elementosCuestionario.getPregunta().trim().isEmpty()) {
            return ResponseEntity.badRequest().body(null);
        }
        ElementosCuestionario nuevoElemento = elementosCuestionarioRepository.save(elementosCuestionario);
        return new ResponseEntity<>(nuevoElemento, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ElementosCuestionario> updateElementosCuestionario(
            @PathVariable Integer id, @RequestBody ElementosCuestionario elementosCuestionarioActualizado) {
        return elementosCuestionarioRepository.findById(id)
                .map(elementosExistentes -> {
                    elementosExistentes.setPregunta(elementosCuestionarioActualizado.getPregunta());
                    elementosExistentes.setTipoPregunta(elementosCuestionarioActualizado.getTipoPregunta());
                    elementosExistentes.setCuestionario(elementosCuestionarioActualizado.getCuestionario());
                    ElementosCuestionario actualizado = elementosCuestionarioRepository.save(elementosExistentes);
                    return ResponseEntity.ok(actualizado);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteElementosCuestionario(@PathVariable Integer id) {
        return elementosCuestionarioRepository.findById(id)
                .map(elementosCuestionario -> {
                    elementosCuestionarioRepository.deleteById(id);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
