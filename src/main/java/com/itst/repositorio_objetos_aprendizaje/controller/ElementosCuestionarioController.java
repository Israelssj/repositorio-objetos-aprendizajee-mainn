package com.itst.repositorio_objetos_aprendizaje.controller;

import com.itst.repositorio_objetos_aprendizaje.model.ElementosCuestionario;
import com.itst.repositorio_objetos_aprendizaje.repository.ElementosCuestionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
        return new ResponseEntity<>(elementosCuestionario, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<ElementosCuestionario> getElementosCuestionarioById(@PathVariable Integer id) {
        Optional<ElementosCuestionario> elementosCuestionarioOptional = elementosCuestionarioRepository.findById(id);
        return elementosCuestionarioOptional.map(elementosCuestionario -> new ResponseEntity<>(elementosCuestionario, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    @PostMapping
    public ResponseEntity<ElementosCuestionario> createElementosCuestionario(@RequestBody ElementosCuestionario elementosCuestionario) {
        ElementosCuestionario nuevoElementosCuestionario = elementosCuestionarioRepository.save(elementosCuestionario);
        return new ResponseEntity<>(nuevoElementosCuestionario, HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    public ResponseEntity<ElementosCuestionario> updateElementosCuestionario(@PathVariable Integer id, @RequestBody ElementosCuestionario elementosCuestionarioActualizado) {
        Optional<ElementosCuestionario> elementosCuestionarioOptional = elementosCuestionarioRepository.findById(id);
        if (elementosCuestionarioOptional.isPresent()) {
            ElementosCuestionario elementosCuestionarioExistente = elementosCuestionarioOptional.get();
            elementosCuestionarioExistente.setPregunta(elementosCuestionarioActualizado.getPregunta());
            elementosCuestionarioExistente.setTipoPregunta(elementosCuestionarioActualizado.getTipoPregunta());
            elementosCuestionarioExistente.setCuestionario(elementosCuestionarioActualizado.getCuestionario());

            ElementosCuestionario elementosCuestionarioActualizadoDB = elementosCuestionarioRepository.save(elementosCuestionarioExistente);
            return new ResponseEntity<>(elementosCuestionarioActualizadoDB, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteElementosCuestionario(@PathVariable Integer id) {
        Optional<ElementosCuestionario> elementosCuestionarioOptional = elementosCuestionarioRepository.findById(id);
        if (elementosCuestionarioOptional.isPresent()) {
            elementosCuestionarioRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
