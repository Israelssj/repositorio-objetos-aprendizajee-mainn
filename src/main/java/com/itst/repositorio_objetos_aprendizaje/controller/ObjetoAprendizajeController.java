package com.itst.repositorio_objetos_aprendizaje.controller;

import com.itst.repositorio_objetos_aprendizaje.model.ObjetoAprendizaje;
import com.itst.repositorio_objetos_aprendizaje.repository.ObjetoAprendizajeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
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

    // Obtener todos los objetos de aprendizaje
    @GetMapping("/")
    public List<ObjetoAprendizaje> getAllObjetosAprendizaje() {
        return objetoAprendizajeRepository.findAll();
    }

    // Obtener un objeto de aprendizaje por su ID
    @GetMapping("/{id}")
    public ResponseEntity<ObjetoAprendizaje> getObjetoAprendizajeById(@PathVariable Integer id) {
        Optional<ObjetoAprendizaje> objetoAprendizaje = objetoAprendizajeRepository.findById(id);
        return objetoAprendizaje.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Crear un nuevo objeto de aprendizaje
    @PostMapping(value = "/", consumes = {"multipart/form-data"})
    public ResponseEntity<?> createObjetoAprendizaje(@RequestParam("file") MultipartFile file) {
        try {
            if (file.isEmpty()) {
                return ResponseEntity.badRequest().body("El archivo está vacío.");
            }
            if (!file.getContentType().equals("application/zip")) {
                return ResponseEntity.badRequest().body("El archivo no tiene un formato válido (debe ser .h5p).");
            }

            // Generar un nombre único para evitar conflictos
            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            String directoryPath = "C:\\Users\\Administrator\\Documents\\ObjetosDeAprendizaje";
            File destinationFile = new File(directoryPath + File.separator + fileName);
            file.transferTo(destinationFile);

            ObjetoAprendizaje objetoAprendizaje = new ObjetoAprendizaje();
            objetoAprendizaje.setArchivo(fileName);
            objetoAprendizaje.setFecha(new Date(System.currentTimeMillis()));

            ObjetoAprendizaje savedObjetoAprendizaje = objetoAprendizajeRepository.save(objetoAprendizaje);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedObjetoAprendizaje);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al guardar el archivo: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error inesperado: " + e.getMessage());
        }
    }

    // Descargar un archivo por su nombre
    @GetMapping("/descargar/{fileName}")
    public ResponseEntity<FileSystemResource> descargarArchivo(@PathVariable String fileName) {
        String directoryPath = "C:\\Users\\Administrator\\Documents\\ObjetosDeAprendizaje";
        File file = new File(directoryPath + File.separator + fileName);

        if (!file.exists()) {
            return ResponseEntity.notFound().build();
        }

        FileSystemResource resource = new FileSystemResource(file);
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + file.getName());

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(file.length())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }

    // Actualizar un objeto de aprendizaje
    @PutMapping("/{id}")
    public ResponseEntity<ObjetoAprendizaje> updateObjetoAprendizaje(
            @PathVariable Integer id,
            @RequestBody ObjetoAprendizaje objetoAprendizajeDetails) {
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

    // Eliminar un objeto de aprendizaje por su ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteObjetoAprendizaje(@PathVariable Integer id) {
        Optional<ObjetoAprendizaje> objetoAprendizaje = objetoAprendizajeRepository.findById(id);
        if (objetoAprendizaje.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        String directoryPath = "C:\\Users\\Administrator\\Documents\\ObjetosDeAprendizaje";
        File file = new File(directoryPath + File.separator + objetoAprendizaje.get().getArchivo());
        if (file.exists() && !file.delete()) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }
        objetoAprendizajeRepository.delete(objetoAprendizaje.get());
        return ResponseEntity.noContent().build();
    }
}
