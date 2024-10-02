package com.itst.repositorio_objetos_aprendizaje.repository;

import com.itst.repositorio_objetos_aprendizaje.model.ArrastrarPalabras;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArrastrarPalabrasRepository extends JpaRepository<ArrastrarPalabras, Integer> {
}
