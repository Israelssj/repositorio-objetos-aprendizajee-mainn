    package com.itst.repositorio_objetos_aprendizaje.controller;

    import com.itst.repositorio_objetos_aprendizaje.model.PresentacionCurso;
    import com.itst.repositorio_objetos_aprendizaje.repository.PresentacionCursoRepository;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.http.HttpStatus;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.*;

    import java.util.List;
    import java.util.Optional;

    @RestController
    @RequestMapping("/api/presentaciones-curso")
    public class PresentacionCursoController {

        private final PresentacionCursoRepository presentacionCursoRepository;

        @Autowired
        public PresentacionCursoController(PresentacionCursoRepository presentacionCursoRepository) {
            this.presentacionCursoRepository = presentacionCursoRepository;
        }


        @GetMapping
        public ResponseEntity<List<PresentacionCurso>> getAllPresentacionesCurso() {
            List<PresentacionCurso> presentacionesCurso = presentacionCursoRepository.findAll();
            return new ResponseEntity<>(presentacionesCurso, HttpStatus.OK);
        }



        @GetMapping("/{id}")
        public ResponseEntity<PresentacionCurso> getPresentacionCursoById(@PathVariable Integer id) {
            Optional<PresentacionCurso> presentacionCursoOptional = presentacionCursoRepository.findById(id);
            return presentacionCursoOptional.map(presentacionCurso -> new ResponseEntity<>(presentacionCurso, HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        }


        @PostMapping
        public ResponseEntity<PresentacionCurso> createPresentacionCurso(@RequestBody PresentacionCurso presentacionCurso) {
            PresentacionCurso nuevaPresentacionCurso = presentacionCursoRepository.save(presentacionCurso);
            return new ResponseEntity<>(nuevaPresentacionCurso, HttpStatus.CREATED);
        }


        @PutMapping("/{id}")
        public ResponseEntity<PresentacionCurso> updatePresentacionCurso(@PathVariable Integer id, @RequestBody PresentacionCurso presentacionCursoActualizado) {
            Optional<PresentacionCurso> presentacionCursoOptional = presentacionCursoRepository.findById(id);
            if (presentacionCursoOptional.isPresent()) {
                PresentacionCurso presentacionCursoExistente = presentacionCursoOptional.get();
                presentacionCursoExistente.setGuion(presentacionCursoActualizado.getGuion());

                PresentacionCurso presentacionCursoActualizadoDB = presentacionCursoRepository.save(presentacionCursoExistente);
                return new ResponseEntity<>(presentacionCursoActualizadoDB, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }


        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deletePresentacionCurso(@PathVariable Integer id) {
            Optional<PresentacionCurso> presentacionCursoOptional = presentacionCursoRepository.findById(id);
            if (presentacionCursoOptional.isPresent()) {
                presentacionCursoRepository.deleteById(id);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
    }
