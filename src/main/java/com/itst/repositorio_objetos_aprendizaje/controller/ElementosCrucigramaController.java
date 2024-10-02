package com.itst.repositorio_objetos_aprendizaje.controller;

import com.itst.repositorio_objetos_aprendizaje.model.ElementosCrucigrama;
import com.itst.repositorio_objetos_aprendizaje.repository.ElementosCrucigramaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/elementos-crucigrama")
@CrossOrigin(origins = "http://localhost:3000")
public class ElementosCrucigramaController {

    private final ElementosCrucigramaRepository elementosCrucigramaRepository;

    @Autowired
    public ElementosCrucigramaController(ElementosCrucigramaRepository elementosCrucigramaRepository) {
        this.elementosCrucigramaRepository = elementosCrucigramaRepository;
    }


    @GetMapping
    public ResponseEntity<List<ElementosCrucigrama>> getAllElementosCrucigrama() {
        List<ElementosCrucigrama> elementosCrucigramaList = elementosCrucigramaRepository.findAll();
        return new ResponseEntity<>(elementosCrucigramaList, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<ElementosCrucigrama> getElementosCrucigramaById(@PathVariable Integer id) {
        Optional<ElementosCrucigrama> elementosCrucigramaOptional = elementosCrucigramaRepository.findById(id);
        return elementosCrucigramaOptional.map(elementosCrucigrama -> new ResponseEntity<>(elementosCrucigrama, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    @PostMapping
    public ResponseEntity<ElementosCrucigrama> createElementosCrucigrama(@RequestBody ElementosCrucigrama elementosCrucigrama) {
        ElementosCrucigrama nuevosElementosCrucigrama = elementosCrucigramaRepository.save(elementosCrucigrama);
        return new ResponseEntity<>(nuevosElementosCrucigrama, HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    public ResponseEntity<ElementosCrucigrama> updateElementosCrucigrama(@PathVariable Integer id, @RequestBody ElementosCrucigrama elementosCrucigramaActualizado) {
        Optional<ElementosCrucigrama> elementosCrucigramaOptional = elementosCrucigramaRepository.findById(id);
        if (elementosCrucigramaOptional.isPresent()) {
            ElementosCrucigrama elementosCrucigramaExistente = elementosCrucigramaOptional.get();
            elementosCrucigramaExistente.setPregunta(elementosCrucigramaActualizado.getPregunta());
            elementosCrucigramaExistente.setPista(elementosCrucigramaActualizado.getPista());
            elementosCrucigramaExistente.setCrucigrama(elementosCrucigramaActualizado.getCrucigrama());

            ElementosCrucigrama elementosCrucigramaActualizadoDB = elementosCrucigramaRepository.save(elementosCrucigramaExistente);
            return new ResponseEntity<>(elementosCrucigramaActualizadoDB, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteElementosCrucigrama(@PathVariable Integer id) {
        Optional<ElementosCrucigrama> elementosCrucigramaOptional = elementosCrucigramaRepository.findById(id);
        if (elementosCrucigramaOptional.isPresent()) {
            elementosCrucigramaRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
