package com.rungroop.login.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rungroop.login.models.Juego;
import com.rungroop.login.models.Resena;

public interface ResenaRepository extends JpaRepository<Resena, Long> {
    List<Resena> findByJuego(Juego juego);
}
