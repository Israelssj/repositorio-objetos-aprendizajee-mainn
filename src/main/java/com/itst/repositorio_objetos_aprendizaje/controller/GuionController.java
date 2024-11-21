package com.itst.repositorio_objetos_aprendizaje.controller;

import com.itst.repositorio_objetos_aprendizaje.model.Guion;
import com.itst.repositorio_objetos_aprendizaje.model.Usuario;
import com.itst.repositorio_objetos_aprendizaje.repository.GuionRepository;
import com.itst.repositorio_objetos_aprendizaje.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/guiones")
@CrossOrigin(origins = "http://localhost:3000")
public class GuionController {

    private final GuionRepository guionRepository;
    private final UsuarioRepository usuarioRepository;

    @Autowired
    public GuionController(GuionRepository guionRepository, UsuarioRepository usuarioRepository) {
        this.guionRepository = guionRepository;
        this.usuarioRepository = usuarioRepository;
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

    @GetMapping("/aprobados")
    public ResponseEntity<List<Guion>> getGuionesAprobados() {
        List<Guion> guionesAprobados = guionRepository.findByEstado("aprobado");
        return new ResponseEntity<>(guionesAprobados, HttpStatus.OK);
    }

    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<Guion>> getGuionesByUsuarioId(@PathVariable Integer idUsuario) {
        List<Guion> guionesUsuario = guionRepository.findByUsuario_IdUsuario(idUsuario);
        return new ResponseEntity<>(guionesUsuario, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Guion> getGuionById(@PathVariable Integer id) {
        Optional<Guion> guionOptional = guionRepository.findById(id);
        return guionOptional.map(guion -> new ResponseEntity<>(guion, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Guion> createGuion(@RequestBody Guion guion) {
        if (guion.getTitulo() == null || guion.getDescripcion() == null || guion.getUsuario() == null || guion.getUsuario().getIdUsuario() == null) {
            return ResponseEntity.badRequest().body(null);
        }

        Optional<Usuario> usuarioOptional = usuarioRepository.findById(guion.getUsuario().getIdUsuario());
        if (!usuarioOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        guion.setUsuario(usuarioOptional.get());
        guion.setFechaCreacion(LocalDate.now().toString());
        Guion nuevoGuion = guionRepository.save(guion);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoGuion);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Guion> updateGuion(@PathVariable Integer id, @RequestBody Guion guionActualizado) {
        Optional<Guion> guionOptional = guionRepository.findById(id);
        if (guionOptional.isPresent()) {
            Guion guionExistente = guionOptional.get();
            guionExistente.setTitulo(guionActualizado.getTitulo());
            guionExistente.setDescripcion(guionActualizado.getDescripcion());
            guionExistente.setFechaCreacion(guionActualizado.getFechaCreacion());
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
