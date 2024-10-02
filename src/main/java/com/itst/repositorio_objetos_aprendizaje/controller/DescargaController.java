package com.itst.repositorio_objetos_aprendizaje.controller;

import com.itst.repositorio_objetos_aprendizaje.model.Descarga;
import com.itst.repositorio_objetos_aprendizaje.repository.DescargaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/descarga")
@CrossOrigin(origins = "http://localhost:3000")
public class DescargaController {

    @Autowired
    private DescargaRepository descargaRepository;

    @GetMapping
    public ResponseEntity<Iterable<Descarga>> findAll() {
        return ResponseEntity.ok(descargaRepository.findAll());
    }

    @GetMapping("/{idDescarga}")
    public ResponseEntity<Descarga> findById(@PathVariable Integer idDescarga) {
        Optional<Descarga> descargaOptional = descargaRepository.findById(idDescarga);
        return descargaOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody Descarga newDescarga, UriComponentsBuilder ucb) {
        Descarga savedDescarga = descargaRepository.save(newDescarga);
        URI uri = ucb.path("/descarga/{idDescarga}").buildAndExpand(savedDescarga.getIdDescarga()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{idDescarga}")
    public ResponseEntity<Void> update(@PathVariable Integer idDescarga, @RequestBody Descarga updatedDescarga) {
        Optional<Descarga> existingDescarga = descargaRepository.findById(idDescarga);
        if (existingDescarga.isPresent()) {
            updatedDescarga.setIdDescarga(existingDescarga.get().getIdDescarga());
            descargaRepository.save(updatedDescarga);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{idDescarga}")
    public ResponseEntity<Void> delete(@PathVariable Integer idDescarga) {
        if (descargaRepository.existsById(idDescarga)) {
            descargaRepository.deleteById(idDescarga);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
