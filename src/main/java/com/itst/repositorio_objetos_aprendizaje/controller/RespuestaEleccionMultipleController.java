package com.itst.repositorio_objetos_aprendizaje.controller;

import com.itst.repositorio_objetos_aprendizaje.model.RespuestaEleccionMultiple;
import com.itst.repositorio_objetos_aprendizaje.repository.RespuestaEleccionMultipleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/respuestas-eleccion-multiple")
public class RespuestaEleccionMultipleController {

    private final RespuestaEleccionMultipleRepository respuestaEleccionMultipleRepository;

    @Autowired
    public RespuestaEleccionMultipleController(RespuestaEleccionMultipleRepository respuestaEleccionMultipleRepository) {
        this.respuestaEleccionMultipleRepository = respuestaEleccionMultipleRepository;
    }


    @GetMapping
    public ResponseEntity<List<RespuestaEleccionMultiple>> getAllRespuestasEleccionMultiple() {
        List<RespuestaEleccionMultiple> respuestasEleccionMultiple = respuestaEleccionMultipleRepository.findAll();
        return new ResponseEntity<>(respuestasEleccionMultiple, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<RespuestaEleccionMultiple> getRespuestaEleccionMultipleById(@PathVariable Integer id) {
        Optional<RespuestaEleccionMultiple> respuestaEleccionMultipleOptional = respuestaEleccionMultipleRepository.findById(id);
        return respuestaEleccionMultipleOptional.map(respuestaEleccionMultiple -> new ResponseEntity<>(respuestaEleccionMultiple, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    @PostMapping
    public ResponseEntity<RespuestaEleccionMultiple> createRespuestaEleccionMultiple(@RequestBody RespuestaEleccionMultiple respuestaEleccionMultiple) {
        RespuestaEleccionMultiple nuevaRespuestaEleccionMultiple = respuestaEleccionMultipleRepository.save(respuestaEleccionMultiple);
        return new ResponseEntity<>(nuevaRespuestaEleccionMultiple, HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    public ResponseEntity<RespuestaEleccionMultiple> updateRespuestaEleccionMultiple(@PathVariable Integer id, @RequestBody RespuestaEleccionMultiple respuestaEleccionMultipleActualizado) {
        Optional<RespuestaEleccionMultiple> respuestaEleccionMultipleOptional = respuestaEleccionMultipleRepository.findById(id);
        if (respuestaEleccionMultipleOptional.isPresent()) {
            RespuestaEleccionMultiple respuestaEleccionMultipleExistente = respuestaEleccionMultipleOptional.get();
            respuestaEleccionMultipleExistente.setTextoRespuesta(respuestaEleccionMultipleActualizado.getTextoRespuesta());
            respuestaEleccionMultipleExistente.setCorrecta(respuestaEleccionMultipleActualizado.getCorrecta());
            respuestaEleccionMultipleExistente.setElementosEleccionMultiple(respuestaEleccionMultipleActualizado.getElementosEleccionMultiple());

            RespuestaEleccionMultiple respuestaEleccionMultipleActualizadoDB = respuestaEleccionMultipleRepository.save(respuestaEleccionMultipleExistente);
            return new ResponseEntity<>(respuestaEleccionMultipleActualizadoDB, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Eliminar una respuesta de elección  por su ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRespuestaEleccionMultiple(@PathVariable Integer id) {
        Optional<RespuestaEleccionMultiple> respuestaEleccionMultipleOptional = respuestaEleccionMultipleRepository.findById(id);
        if (respuestaEleccionMultipleOptional.isPresent()) {
            respuestaEleccionMultipleRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
