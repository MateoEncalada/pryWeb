package com.rungroop.login.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.rungroop.login.dto.JuegoDto;
import com.rungroop.login.models.Genero;
import com.rungroop.login.models.Juego;
import com.rungroop.login.repository.JuegoRepository;
import com.rungroop.login.service.JuegoService;

@Service
public class JuegoServiceImpl implements JuegoService {
    private JuegoRepository juegoRepository;
    public JuegoServiceImpl(JuegoRepository juegoRepository) {
        this.juegoRepository = juegoRepository;
    }

    @Override
    public List<JuegoDto> findAllJuegos() {
        List<Juego> juegos = juegoRepository.findAll();
        return juegos.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public JuegoDto findJuegoById(Long id) {
        Juego juego = juegoRepository.findById(id).orElseThrow(() -> new RuntimeException("Juego not found"));
        return mapToDto(juego);
    }

    @Override
    public JuegoDto saveJuego(JuegoDto juegoDto) {
        Juego juego = mapToEntity(juegoDto);
        Juego savedJuego = juegoRepository.save(juego);
        return mapToDto(savedJuego);
    }

    @Override
    public void deleteJuego(Long id) {
        juegoRepository.deleteById(id);
    }

    private JuegoDto mapToDto(Juego juego) {
        return JuegoDto.builder()
                .jue_id(juego.getJue_id())
                .jue_Titulo(juego.getJue_Titulo())
                .jue_Descripcion(juego.getJue_Descripcion())
                .jue_FechaLanzamiento(juego.getJue_FechaLanzamiento())
                .jue_Precio(juego.getJue_Precio())
                .jue_Inventario(juego.getJue_Inventario())
                .gen_id(juego.getGenero().getGen_id())
                .build();
    }

    private Juego mapToEntity(JuegoDto juegoDto) {
        Genero genero = new Genero();
        genero.setGen_id(juegoDto.getGen_id());

        return Juego.builder()
                .jue_id(juegoDto.getJue_id())
                .jue_Titulo(juegoDto.getJue_Titulo())
                .jue_Descripcion(juegoDto.getJue_Descripcion())
                .jue_FechaLanzamiento(juegoDto.getJue_FechaLanzamiento())
                .jue_Precio(juegoDto.getJue_Precio())
                .jue_Inventario(juegoDto.getJue_Inventario())
                .genero(genero)
                .build();
    }

}
