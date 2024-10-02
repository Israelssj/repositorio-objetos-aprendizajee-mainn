package com.itst.repositorio_objetos_aprendizaje.controller;

import com.itst.repositorio_objetos_aprendizaje.model.EleccionMultiple;
import com.itst.repositorio_objetos_aprendizaje.repository.EleccionMultipleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/eleccion-multiple")
public class EleccionMultipleController {

    private final EleccionMultipleRepository eleccionMultipleRepository;

    @Autowired
    public EleccionMultipleController(EleccionMultipleRepository eleccionMultipleRepository) {
        this.eleccionMultipleRepository = eleccionMultipleRepository;
    }


    @GetMapping
    public ResponseEntity<List<EleccionMultiple>> getAllEleccionMultiple() {
        List<EleccionMultiple> eleccionesMultiples = eleccionMultipleRepository.findAll();
        return new ResponseEntity<>(eleccionesMultiples, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<EleccionMultiple> getEleccionMultipleById(@PathVariable Integer id) {
        Optional<EleccionMultiple> eleccionMultipleOptional = eleccionMultipleRepository.findById(id);
        return eleccionMultipleOptional.map(eleccionMultiple -> new ResponseEntity<>(eleccionMultiple, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    @PostMapping
    public ResponseEntity<EleccionMultiple> createEleccionMultiple(@RequestBody EleccionMultiple eleccionMultiple) {
        EleccionMultiple nuevaEleccionMultiple = eleccionMultipleRepository.save(eleccionMultiple);
        return new ResponseEntity<>(nuevaEleccionMultiple, HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    public ResponseEntity<EleccionMultiple> updateEleccionMultiple(@PathVariable Integer id, @RequestBody EleccionMultiple eleccionMultipleActualizado) {
        Optional<EleccionMultiple> eleccionMultipleOptional = eleccionMultipleRepository.findById(id);
        if (eleccionMultipleOptional.isPresent()) {
            EleccionMultiple eleccionMultipleExistente = eleccionMultipleOptional.get();
            eleccionMultipleExistente.setGuion(eleccionMultipleActualizado.getGuion());
            eleccionMultipleExistente.setElementosEleccionMultiple(eleccionMultipleActualizado.getElementosEleccionMultiple());

            EleccionMultiple eleccionMultipleActualizadaDB = eleccionMultipleRepository.save(eleccionMultipleExistente);
            return new ResponseEntity<>(eleccionMultipleActualizadaDB, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEleccionMultiple(@PathVariable Integer id) {
        Optional<EleccionMultiple> eleccionMultipleOptional = eleccionMultipleRepository.findById(id);
        if (eleccionMultipleOptional.isPresent()) {
            eleccionMultipleRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
