package com.itst.repositorio_objetos_aprendizaje.repository;

import com.itst.repositorio_objetos_aprendizaje.model.Guion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GuionRepository extends JpaRepository<Guion, Integer> {
    List<Guion> findByEstado(String estado);
    List<Guion> findByUsuario_IdUsuario(Integer idUsuario);
}
