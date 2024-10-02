package com.itst.repositorio_objetos_aprendizaje.controller;

import com.itst.repositorio_objetos_aprendizaje.model.ArrastrarPalabras;
import com.itst.repositorio_objetos_aprendizaje.repository.ArrastrarPalabrasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/arrastrar-palabras")
public class ArrastrarPalabrasController {

    private final ArrastrarPalabrasRepository arrastrarPalabrasRepository;

    @Autowired
    public ArrastrarPalabrasController(ArrastrarPalabrasRepository arrastrarPalabrasRepository) {
        this.arrastrarPalabrasRepository = arrastrarPalabrasRepository;
    }


    @GetMapping
    public ResponseEntity<List<ArrastrarPalabras>> getAllArrastrarPalabras() {
        List<ArrastrarPalabras> arrastrarPalabrasList = arrastrarPalabrasRepository.findAll();
        return new ResponseEntity<>(arrastrarPalabrasList, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<ArrastrarPalabras> getArrastrarPalabrasById(@PathVariable Integer id) {
        Optional<ArrastrarPalabras> arrastrarPalabrasOptional = arrastrarPalabrasRepository.findById(id);
        return arrastrarPalabrasOptional.map(arrastrarPalabras -> new ResponseEntity<>(arrastrarPalabras, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    @PostMapping
    public ResponseEntity<ArrastrarPalabras> createArrastrarPalabras(@RequestBody ArrastrarPalabras arrastrarPalabras) {
        ArrastrarPalabras nuevaArrastrarPalabras = arrastrarPalabrasRepository.save(arrastrarPalabras);
        return new ResponseEntity<>(nuevaArrastrarPalabras, HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    public ResponseEntity<ArrastrarPalabras> updateArrastrarPalabras(@PathVariable Integer id, @RequestBody ArrastrarPalabras arrastrarPalabrasActualizado) {
        Optional<ArrastrarPalabras> arrastrarPalabrasOptional = arrastrarPalabrasRepository.findById(id);
        if (arrastrarPalabrasOptional.isPresent()) {
            ArrastrarPalabras arrastrarPalabrasExistente = arrastrarPalabrasOptional.get();
            arrastrarPalabrasExistente.setTextoBase(arrastrarPalabrasActualizado.getTextoBase());
            arrastrarPalabrasExistente.setGuion(arrastrarPalabrasActualizado.getGuion());

            ArrastrarPalabras arrastrarPalabrasActualizadoDB = arrastrarPalabrasRepository.save(arrastrarPalabrasExistente);
            return new ResponseEntity<>(arrastrarPalabrasActualizadoDB, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArrastrarPalabras(@PathVariable Integer id) {
        Optional<ArrastrarPalabras> arrastrarPalabrasOptional = arrastrarPalabrasRepository.findById(id);
        if (arrastrarPalabrasOptional.isPresent()) {
            arrastrarPalabrasRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
