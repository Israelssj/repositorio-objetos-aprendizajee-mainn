package com.itst.repositorio_objetos_aprendizaje.controller;

import com.itst.repositorio_objetos_aprendizaje.model.ElementosEleccionMultiple;
import com.itst.repositorio_objetos_aprendizaje.repository.ElementosEleccionMultipleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/elementos-eleccion-multiple")
public class ElementosEleccionMultipleController {

    private final ElementosEleccionMultipleRepository elementosEleccionMultipleRepository;

    @Autowired
    public ElementosEleccionMultipleController(ElementosEleccionMultipleRepository elementosEleccionMultipleRepository) {
        this.elementosEleccionMultipleRepository = elementosEleccionMultipleRepository;
    }


    @GetMapping
    public ResponseEntity<List<ElementosEleccionMultiple>> getAllElementosEleccionMultiple() {
        List<ElementosEleccionMultiple> elementosEleccionMultiple = elementosEleccionMultipleRepository.findAll();
        return new ResponseEntity<>(elementosEleccionMultiple, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<ElementosEleccionMultiple> getElementosEleccionMultipleById(@PathVariable Integer id) {
        Optional<ElementosEleccionMultiple> elementosEleccionMultipleOptional = elementosEleccionMultipleRepository.findById(id);
        return elementosEleccionMultipleOptional.map(elementosEleccionMultiple -> new ResponseEntity<>(elementosEleccionMultiple, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    @PostMapping
    public ResponseEntity<ElementosEleccionMultiple> createElementosEleccionMultiple(@RequestBody ElementosEleccionMultiple elementosEleccionMultiple) {
        ElementosEleccionMultiple nuevoElementosEleccionMultiple = elementosEleccionMultipleRepository.save(elementosEleccionMultiple);
        return new ResponseEntity<>(nuevoElementosEleccionMultiple, HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    public ResponseEntity<ElementosEleccionMultiple> updateElementosEleccionMultiple(@PathVariable Integer id, @RequestBody ElementosEleccionMultiple elementosEleccionMultipleActualizado) {
        Optional<ElementosEleccionMultiple> elementosEleccionMultipleOptional = elementosEleccionMultipleRepository.findById(id);
        if (elementosEleccionMultipleOptional.isPresent()) {
            ElementosEleccionMultiple elementosEleccionMultipleExistente = elementosEleccionMultipleOptional.get();
            elementosEleccionMultipleExistente.setPregunta(elementosEleccionMultipleActualizado.getPregunta());
            elementosEleccionMultipleExistente.setRespuestas(elementosEleccionMultipleActualizado.getRespuestas());

            ElementosEleccionMultiple elementosEleccionMultipleActualizadoDB = elementosEleccionMultipleRepository.save(elementosEleccionMultipleExistente);
            return new ResponseEntity<>(elementosEleccionMultipleActualizadoDB, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteElementosEleccionMultiple(@PathVariable Integer id) {
        Optional<ElementosEleccionMultiple> elementosEleccionMultipleOptional = elementosEleccionMultipleRepository.findById(id);
        if (elementosEleccionMultipleOptional.isPresent()) {
            elementosEleccionMultipleRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
