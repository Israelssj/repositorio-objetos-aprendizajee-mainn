package com.itst.repositorio_objetos_aprendizaje.controller;

import com.itst.repositorio_objetos_aprendizaje.model.RespuestaCuestionario;
import com.itst.repositorio_objetos_aprendizaje.repository.RespuestaCuestionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/respuestas-cuestionario")
public class RespuestaCuestionarioController {

    private final RespuestaCuestionarioRepository respuestaCuestionarioRepository;

    @Autowired
    public RespuestaCuestionarioController(RespuestaCuestionarioRepository respuestaCuestionarioRepository) {
        this.respuestaCuestionarioRepository = respuestaCuestionarioRepository;
    }


    @GetMapping
    public ResponseEntity<List<RespuestaCuestionario>> getAllRespuestasCuestionario() {
        List<RespuestaCuestionario> respuestasCuestionario = respuestaCuestionarioRepository.findAll();
        return new ResponseEntity<>(respuestasCuestionario, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<RespuestaCuestionario> getRespuestaCuestionarioById(@PathVariable Integer id) {
        Optional<RespuestaCuestionario> respuestaCuestionarioOptional = respuestaCuestionarioRepository.findById(id);
        return respuestaCuestionarioOptional.map(respuestaCuestionario -> new ResponseEntity<>(respuestaCuestionario, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    @PostMapping
    public ResponseEntity<RespuestaCuestionario> createRespuestaCuestionario(@RequestBody RespuestaCuestionario respuestaCuestionario) {
        RespuestaCuestionario nuevaRespuestaCuestionario = respuestaCuestionarioRepository.save(respuestaCuestionario);
        return new ResponseEntity<>(nuevaRespuestaCuestionario, HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    public ResponseEntity<RespuestaCuestionario> updateRespuestaCuestionario(@PathVariable Integer id, @RequestBody RespuestaCuestionario respuestaCuestionarioActualizado) {
        Optional<RespuestaCuestionario> respuestaCuestionarioOptional = respuestaCuestionarioRepository.findById(id);
        if (respuestaCuestionarioOptional.isPresent()) {
            RespuestaCuestionario respuestaCuestionarioExistente = respuestaCuestionarioOptional.get();
            respuestaCuestionarioExistente.setTexto(respuestaCuestionarioActualizado.getTexto());
            respuestaCuestionarioExistente.setElementosCuestionario(respuestaCuestionarioActualizado.getElementosCuestionario());

            RespuestaCuestionario respuestaCuestionarioActualizadoDB = respuestaCuestionarioRepository.save(respuestaCuestionarioExistente);
            return new ResponseEntity<>(respuestaCuestionarioActualizadoDB, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRespuestaCuestionario(@PathVariable Integer id) {
        Optional<RespuestaCuestionario> respuestaCuestionarioOptional = respuestaCuestionarioRepository.findById(id);
        if (respuestaCuestionarioOptional.isPresent()) {
            respuestaCuestionarioRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
