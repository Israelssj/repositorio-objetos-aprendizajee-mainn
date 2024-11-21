package com.itst.repositorio_objetos_aprendizaje.controller;

import com.itst.repositorio_objetos_aprendizaje.model.Guion;
import com.itst.repositorio_objetos_aprendizaje.model.ObjetoAprendizaje;
import com.itst.repositorio_objetos_aprendizaje.repository.GuionRepository;
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
    private final GuionRepository guionRepository;

    @Autowired
    public ObjetoAprendizajeController(ObjetoAprendizajeRepository objetoAprendizajeRepository, GuionRepository guionRepository) {
        this.objetoAprendizajeRepository = objetoAprendizajeRepository;
        this.guionRepository = guionRepository;
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

    // Crear un nuevo objeto de aprendizaje asociado a un guion aprobado
    @PostMapping(value = "/", consumes = {"multipart/form-data"})
    public ResponseEntity<?> createObjetoAprendizaje(
            @RequestParam("file") MultipartFile file,
            @RequestParam("idGuion") Integer idGuion) {
        try {
            if (file.isEmpty()) {
                return ResponseEntity.badRequest().body("El archivo está vacío.");
            }

            // Buscar el Guion por ID
            Optional<Guion> optionalGuion = guionRepository.findById(idGuion);
            if (optionalGuion.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Guion no encontrado.");
            }

            Guion guion = optionalGuion.get();

            // Verificar que el Guion esté en estado "aprobado"
            if (!"aprobado".equalsIgnoreCase(guion.getEstado())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("El guion seleccionado no está en estado 'aprobado'.");
            }


            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            String directoryPath = "C:\\Users\\Administrator\\Documents\\ObjetosDeAprendizaje";
            File destinationFile = new File(directoryPath + File.separator + fileName);
            file.transferTo(destinationFile);

            ObjetoAprendizaje objetoAprendizaje = new ObjetoAprendizaje();
            objetoAprendizaje.setArchivo(fileName);
            objetoAprendizaje.setFecha(new Date(System.currentTimeMillis()));
            objetoAprendizaje.setGuion(guion);


            ObjetoAprendizaje savedObjetoAprendizaje = objetoAprendizajeRepository.save(objetoAprendizaje);

            // estado del Guion a "publicado"
            guion.setEstado("publicado");
            guionRepository.save(guion);

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
