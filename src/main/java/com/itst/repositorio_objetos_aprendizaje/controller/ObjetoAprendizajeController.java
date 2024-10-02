package com.itst.repositorio_objetos_aprendizaje.controller;

import com.itst.repositorio_objetos_aprendizaje.model.ObjetoAprendizaje;
import com.itst.repositorio_objetos_aprendizaje.repository.ObjetoAprendizajeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/objetos-aprendizaje")
@CrossOrigin(origins = "http://localhost:3000")
public class ObjetoAprendizajeController {

    private final ObjetoAprendizajeRepository objetoAprendizajeRepository;

    @Autowired
    public ObjetoAprendizajeController(ObjetoAprendizajeRepository objetoAprendizajeRepository) {
        this.objetoAprendizajeRepository = objetoAprendizajeRepository;
    }

    @GetMapping("/")
    public List<ObjetoAprendizaje> getAllObjetosAprendizaje() {
        return objetoAprendizajeRepository.findAll();
    }


    @GetMapping("/{id}")
    public ResponseEntity<ObjetoAprendizaje> getObjetoAprendizajeById(@PathVariable Integer id) {
        Optional<ObjetoAprendizaje> objetoAprendizaje = objetoAprendizajeRepository.findById(id);
        return objetoAprendizaje.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }


    @PostMapping(value = "/", consumes = {"multipart/form-data"})
    public ResponseEntity<ObjetoAprendizaje> createObjetoAprendizaje(@RequestParam("file") MultipartFile file) {
        try {

            String fileName = file.getOriginalFilename();


            ObjetoAprendizaje objetoAprendizaje = new ObjetoAprendizaje();
            objetoAprendizaje.setArchivo(fileName);
            objetoAprendizaje.setFecha(new Date(System.currentTimeMillis()));

            ObjetoAprendizaje savedObjetoAprendizaje = objetoAprendizajeRepository.save(objetoAprendizaje);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedObjetoAprendizaje);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ObjetoAprendizaje> updateObjetoAprendizaje(@PathVariable Integer id, @RequestBody ObjetoAprendizaje objetoAprendizajeDetails) {
        Optional<ObjetoAprendizaje> optionalObjetoAprendizaje = objetoAprendizajeRepository.findById(id);
        if (optionalObjetoAprendizaje.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        ObjetoAprendizaje objetoAprendizaje = optionalObjetoAprendizaje.get();
        objetoAprendizaje.setArchivo(objetoAprendizajeDetails.getArchivo());
        objetoAprendizaje.setFecha(objetoAprendizajeDetails.getFecha());
        ObjetoAprendizaje updatedObjetoAprendizaje = objetoAprendizajeRepository.save(objetoAprendizaje);
        return ResponseEntity.ok(updatedObjetoAprendizaje);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteObjetoAprendizaje(@PathVariable Integer id) {
        Optional<ObjetoAprendizaje> objetoAprendizaje = objetoAprendizajeRepository.findById(id);
        if (objetoAprendizaje.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        objetoAprendizajeRepository.delete(objetoAprendizaje.get());
        return ResponseEntity.noContent().build();
    }
}
