package com.itst.repositorio_objetos_aprendizaje.controller;

import com.itst.repositorio_objetos_aprendizaje.model.Guion;
import com.itst.repositorio_objetos_aprendizaje.repository.GuionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/guiones")
@CrossOrigin(origins = "http://localhost:3000")
public class GuionController {

    private final GuionRepository guionRepository;

    @Autowired
    public GuionController(GuionRepository guionRepository) {
        this.guionRepository = guionRepository;
    }

    @GetMapping
    public ResponseEntity<List<Guion>> getAllGuiones() {
        List<Guion> guiones = guionRepository.findAll();
        return new ResponseEntity<>(guiones, HttpStatus.OK);
    }

    @GetMapping("/pendientes")
    public ResponseEntity<List<Guion>> getGuionesPendientes() {
        List<Guion> guionesPendientes = guionRepository.findByEstado("pendiente");
        return new ResponseEntity<>(guionesPendientes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Guion> getGuionById(@PathVariable Integer id) {
        Optional<Guion> guionOptional = guionRepository.findById(id);
        return guionOptional.map(guion -> new ResponseEntity<>(guion, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Guion> createGuion(@RequestBody Guion guion) {
        if (guion.getTitulo() == null || guion.getDescripcion() == null) {
            return ResponseEntity.badRequest().build();
        }

        Guion nuevoGuion = guionRepository.save(guion);
        return new ResponseEntity<>(nuevoGuion, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Guion> updateGuion(@PathVariable Integer id, @RequestBody Guion guionActualizado) {
        Optional<Guion> guionOptional = guionRepository.findById(id);
        if (guionOptional.isPresent()) {
            Guion guionExistente = guionOptional.get();
            guionExistente.setTitulo(guionActualizado.getTitulo());
            guionExistente.setDescripcion(guionActualizado.getDescripcion());
            guionExistente.setFechaCreacion(guionActualizado.getFechaCreacion());
            guionExistente.setMateria(guionActualizado.getMateria());
            guionExistente.setNombreDocente(guionActualizado.getNombreDocente());
            guionExistente.setTipoObjeto(guionActualizado.getTipoObjeto());
            guionExistente.setObjetoAprendizaje(guionActualizado.getObjetoAprendizaje());

            Guion guionActualizadoDB = guionRepository.save(guionExistente);
            return new ResponseEntity<>(guionActualizadoDB, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/aprobar/{id}")
    public ResponseEntity<Void> aprobarGuion(@PathVariable Integer id) {
        Optional<Guion> guionOptional = guionRepository.findById(id);
        if (guionOptional.isPresent()) {
            Guion guion = guionOptional.get();
            guion.setEstado("aprobado");
            guionRepository.save(guion);
            return ResponseEntity.ok().build();
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/denegar/{id}")
    public ResponseEntity<Void> denegarGuion(@PathVariable Integer id, @RequestBody String observacion) {
        Optional<Guion> guionOptional = guionRepository.findById(id);
        if (guionOptional.isPresent()) {
            Guion guion = guionOptional.get();
            guion.setEstado("denegado");
            guion.setObservacion(observacion);
            guionRepository.save(guion);
            return ResponseEntity.ok().build();
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGuion(@PathVariable Integer id) {
        Optional<Guion> guionOptional = guionRepository.findById(id);
        if (guionOptional.isPresent()) {
            guionRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
