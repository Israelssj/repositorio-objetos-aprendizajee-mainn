package com.itst.repositorio_objetos_aprendizaje.controller;

import com.itst.repositorio_objetos_aprendizaje.model.RespuestaCuestionario;
import com.itst.repositorio_objetos_aprendizaje.repository.RespuestaCuestionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        return ResponseEntity.ok(respuestasCuestionario);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RespuestaCuestionario> getRespuestaCuestionarioById(@PathVariable Integer id) {
        return respuestaCuestionarioRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<RespuestaCuestionario> createRespuestaCuestionario(@RequestBody RespuestaCuestionario respuestaCuestionario) {
        if (respuestaCuestionario.getTexto() == null || respuestaCuestionario.getTexto().trim().isEmpty()) {
            return ResponseEntity.badRequest().body(null);
        }
        RespuestaCuestionario nuevaRespuesta = respuestaCuestionarioRepository.save(respuestaCuestionario);
        return new ResponseEntity<>(nuevaRespuesta, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RespuestaCuestionario> updateRespuestaCuestionario(
            @PathVariable Integer id, @RequestBody RespuestaCuestionario respuestaActualizada) {
        return respuestaCuestionarioRepository.findById(id)
                .map(respuestaExistente -> {
                    respuestaExistente.setTexto(respuestaActualizada.getTexto());
                    respuestaExistente.setCorrecta(respuestaActualizada.getCorrecta());
                    respuestaExistente.setElementosCuestionario(respuestaActualizada.getElementosCuestionario());
                    RespuestaCuestionario actualizada = respuestaCuestionarioRepository.save(respuestaExistente);
                    return ResponseEntity.ok(actualizada);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteRespuestaCuestionario(@PathVariable Integer id) {
        return respuestaCuestionarioRepository.findById(id)
                .map(respuesta -> {
                    respuestaCuestionarioRepository.deleteById(id);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
