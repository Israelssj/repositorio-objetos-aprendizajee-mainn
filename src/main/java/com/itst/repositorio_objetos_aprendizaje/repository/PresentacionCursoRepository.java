package com.itst.repositorio_objetos_aprendizaje.repository;

import com.itst.repositorio_objetos_aprendizaje.model.PresentacionCurso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PresentacionCursoRepository extends JpaRepository<PresentacionCurso, Integer> {
}
