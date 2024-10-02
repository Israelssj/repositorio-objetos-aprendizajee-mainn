package com.itst.repositorio_objetos_aprendizaje.controller;

import com.itst.repositorio_objetos_aprendizaje.model.VideoInteractivo;
import com.itst.repositorio_objetos_aprendizaje.repository.VideoInteractivoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/videos-interactivos")
@CrossOrigin(origins = "http://localhost:3000")
public class VideoInteractivoController {

    private final VideoInteractivoRepository videoInteractivoRepository;

    @Autowired
    public VideoInteractivoController(VideoInteractivoRepository videoInteractivoRepository) {
        this.videoInteractivoRepository = videoInteractivoRepository;
    }


    @GetMapping
    public ResponseEntity<List<VideoInteractivo>> getAllVideosInteractivos() {
        List<VideoInteractivo> videosInteractivos = videoInteractivoRepository.findAll();
        return new ResponseEntity<>(videosInteractivos, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<VideoInteractivo> getVideoInteractivoById(@PathVariable Integer id) {
        Optional<VideoInteractivo> videoInteractivoOptional = videoInteractivoRepository.findById(id);
        return videoInteractivoOptional.map(videoInteractivo -> new ResponseEntity<>(videoInteractivo, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    @PostMapping
    public ResponseEntity<VideoInteractivo> createVideoInteractivo(@RequestBody VideoInteractivo videoInteractivo) {
        VideoInteractivo nuevoVideoInteractivo = videoInteractivoRepository.save(videoInteractivo);
        return new ResponseEntity<>(nuevoVideoInteractivo, HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    public ResponseEntity<VideoInteractivo> updateVideoInteractivo(@PathVariable Integer id, @RequestBody VideoInteractivo videoInteractivoActualizado) {
        Optional<VideoInteractivo> videoInteractivoOptional = videoInteractivoRepository.findById(id);
        if (videoInteractivoOptional.isPresent()) {
            VideoInteractivo videoInteractivoExistente = videoInteractivoOptional.get();
            videoInteractivoExistente.setUrlVideo(videoInteractivoActualizado.getUrlVideo());
            videoInteractivoExistente.setGuion(videoInteractivoActualizado.getGuion());

            VideoInteractivo videoInteractivoActualizadoDB = videoInteractivoRepository.save(videoInteractivoExistente);
            return new ResponseEntity<>(videoInteractivoActualizadoDB, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVideoInteractivo(@PathVariable Integer id) {
        Optional<VideoInteractivo> videoInteractivoOptional = videoInteractivoRepository.findById(id);
        if (videoInteractivoOptional.isPresent()) {
            videoInteractivoRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
