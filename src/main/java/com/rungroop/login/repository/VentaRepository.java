package com.rungroop.login.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rungroop.login.models.Juego;
import com.rungroop.login.models.Venta;

public interface VentaRepository extends JpaRepository<Venta, Long> {
    List<Venta> findByJuego(Juego juego);
}
