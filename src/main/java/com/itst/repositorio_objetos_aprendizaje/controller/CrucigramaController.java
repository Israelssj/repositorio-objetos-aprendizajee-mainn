package com.itst.repositorio_objetos_aprendizaje.controller;

import com.itst.repositorio_objetos_aprendizaje.model.Crucigrama;
import com.itst.repositorio_objetos_aprendizaje.repository.CrucigramaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/crucigramas")
@CrossOrigin(origins = "http://localhost:3000")
public class CrucigramaController {

    private final CrucigramaRepository crucigramaRepository;

    @Autowired
    public CrucigramaController(CrucigramaRepository crucigramaRepository) {
        this.crucigramaRepository = crucigramaRepository;
    }


    @GetMapping
    public ResponseEntity<List<Crucigrama>> getAllCrucigramas() {
        List<Crucigrama> crucigramaList = crucigramaRepository.findAll();
        return new ResponseEntity<>(crucigramaList, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Crucigrama> getCrucigramaById(@PathVariable Integer id) {
        Optional<Crucigrama> crucigramaOptional = crucigramaRepository.findById(id);
        return crucigramaOptional.map(crucigrama -> new ResponseEntity<>(crucigrama, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    @PostMapping
    public ResponseEntity<Crucigrama> createCrucigrama(@RequestBody Crucigrama crucigrama) {
        Crucigrama nuevoCrucigrama = crucigramaRepository.save(crucigrama);
        return new ResponseEntity<>(nuevoCrucigrama, HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Crucigrama> updateCrucigrama(@PathVariable Integer id, @RequestBody Crucigrama crucigramaActualizado) {
        Optional<Crucigrama> crucigramaOptional = crucigramaRepository.findById(id);
        if (crucigramaOptional.isPresent()) {
            Crucigrama crucigramaExistente = crucigramaOptional.get();
            crucigramaExistente.setContenido(crucigramaActualizado.getContenido());
            crucigramaExistente.setGuion(crucigramaActualizado.getGuion());

            Crucigrama crucigramaActualizadoDB = crucigramaRepository.save(crucigramaExistente);
            return new ResponseEntity<>(crucigramaActualizadoDB, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCrucigrama(@PathVariable Integer id) {
        Optional<Crucigrama> crucigramaOptional = crucigramaRepository.findById(id);
        if (crucigramaOptional.isPresent()) {
            crucigramaRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
