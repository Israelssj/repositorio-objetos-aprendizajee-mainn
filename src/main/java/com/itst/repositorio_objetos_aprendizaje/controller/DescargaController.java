package com.itst.repositorio_objetos_aprendizaje.controller;

import com.itst.repositorio_objetos_aprendizaje.model.ObjetoAprendizaje;
import com.itst.repositorio_objetos_aprendizaje.repository.ObjetoAprendizajeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/api/descargas")
@CrossOrigin(origins = "http://localhost:3000")
public class DescargaController {

    @Autowired
    private ObjetoAprendizajeRepository objetoAprendizajeRepository;

    // Obtiene todos los objetos de aprendizaje
    @GetMapping
    public ResponseEntity<List<ObjetoAprendizaje>> findAll() {
        List<ObjetoAprendizaje> objetos = objetoAprendizajeRepository.findAll();
        return ResponseEntity.ok(objetos);
    }

    // Maneja la descarga de un objeto de aprendizaje específico
    @GetMapping("/download/{id}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable Integer id) {
        ObjetoAprendizaje objeto = objetoAprendizajeRepository.findById(id).orElse(null);
        if (objeto == null) {
            return ResponseEntity.notFound().build();
        }
        try {

            Path path = Paths.get("ruta/donde/se/guardan/los/archivos/" + objeto.getArchivo());
            byte[] data = Files.readAllBytes(path);
            return ResponseEntity.ok()
                    .header("Content-Disposition", "attachment; filename=\"" + objeto.getArchivo() + "\"")
                    .body(data);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
