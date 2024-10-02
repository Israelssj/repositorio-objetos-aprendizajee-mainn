package com.itst.repositorio_objetos_aprendizaje.controller;

import com.itst.repositorio_objetos_aprendizaje.model.ElementosVideoInteractivo;
import com.itst.repositorio_objetos_aprendizaje.repository.ElementosVideoInteractivoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/elementos-video-interactivo")
public class ElementosVideoInteractivoController {

    private final ElementosVideoInteractivoRepository elementosVideoInteractivoRepository;

    @Autowired
    public ElementosVideoInteractivoController(ElementosVideoInteractivoRepository elementosVideoInteractivoRepository) {
        this.elementosVideoInteractivoRepository = elementosVideoInteractivoRepository;
    }


    @GetMapping
    public ResponseEntity<List<ElementosVideoInteractivo>> getAllElementosVideoInteractivo() {
        List<ElementosVideoInteractivo> elementosVideoInteractivo = elementosVideoInteractivoRepository.findAll();
        return new ResponseEntity<>(elementosVideoInteractivo, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<ElementosVideoInteractivo> getElementosVideoInteractivoById(@PathVariable Integer id) {
        Optional<ElementosVideoInteractivo> elementosVideoInteractivoOptional = elementosVideoInteractivoRepository.findById(id);
        return elementosVideoInteractivoOptional.map(elementosVideoInteractivo -> new ResponseEntity<>(elementosVideoInteractivo, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    @PostMapping
    public ResponseEntity<ElementosVideoInteractivo> createElementosVideoInteractivo(@RequestBody ElementosVideoInteractivo elementosVideoInteractivo) {
        ElementosVideoInteractivo nuevoElementosVideoInteractivo = elementosVideoInteractivoRepository.save(elementosVideoInteractivo);
        return new ResponseEntity<>(nuevoElementosVideoInteractivo, HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    public ResponseEntity<ElementosVideoInteractivo> updateElementosVideoInteractivo(@PathVariable Integer id, @RequestBody ElementosVideoInteractivo elementosVideoInteractivoActualizado) {
        Optional<ElementosVideoInteractivo> elementosVideoInteractivoOptional = elementosVideoInteractivoRepository.findById(id);
        if (elementosVideoInteractivoOptional.isPresent()) {
            ElementosVideoInteractivo elementosVideoInteractivoExistente = elementosVideoInteractivoOptional.get();
            elementosVideoInteractivoExistente.setPregunta(elementosVideoInteractivoActualizado.getPregunta());
            elementosVideoInteractivoExistente.setTipoPregunta(elementosVideoInteractivoActualizado.getTipoPregunta());
            elementosVideoInteractivoExistente.setMinutoVideo(elementosVideoInteractivoActualizado.getMinutoVideo());
            elementosVideoInteractivoExistente.setVideoInteractivo(elementosVideoInteractivoActualizado.getVideoInteractivo());

            ElementosVideoInteractivo elementosVideoInteractivoActualizadoDB = elementosVideoInteractivoRepository.save(elementosVideoInteractivoExistente);
            return new ResponseEntity<>(elementosVideoInteractivoActualizadoDB, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteElementosVideoInteractivo(@PathVariable Integer id) {
        Optional<ElementosVideoInteractivo> elementosVideoInteractivoOptional = elementosVideoInteractivoRepository.findById(id);
        if (elementosVideoInteractivoOptional.isPresent()) {
            elementosVideoInteractivoRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
