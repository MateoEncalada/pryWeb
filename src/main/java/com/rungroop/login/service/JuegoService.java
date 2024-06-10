package com.rungroop.login.service;

import java.util.List;

import com.rungroop.login.dto.JuegoDto;

public interface JuegoService {
    List<JuegoDto> findAllJuegos();
    JuegoDto findJuegoById(Long id);
    JuegoDto saveJuego(JuegoDto juegoDto);
    void deleteJuego(Long id);

}
