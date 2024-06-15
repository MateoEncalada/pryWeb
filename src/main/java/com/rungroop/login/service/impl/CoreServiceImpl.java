package com.rungroop.login.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.rungroop.login.dto.CoreDto;
import com.rungroop.login.models.Juego;
import com.rungroop.login.models.Resena;
import com.rungroop.login.models.Venta;
import com.rungroop.login.repository.JuegoRepository;
import com.rungroop.login.repository.ResenaRepository;
import com.rungroop.login.repository.VentaRepository;
import com.rungroop.login.service.CoreService;

@Service
public class CoreServiceImpl implements CoreService {
    private JuegoRepository juegoRepository;
    private VentaRepository ventaRepository;
    private ResenaRepository resenaRepository;

    public CoreServiceImpl(JuegoRepository juegoRepository, VentaRepository ventaRepository, ResenaRepository resenaRepository) {
        this.juegoRepository = juegoRepository;
        this.ventaRepository = ventaRepository;
        this.resenaRepository = resenaRepository;
    }


    @Override
    public List<CoreDto> compararJuegosConVentasYResenas() {
        List<Juego> juegos = juegoRepository.findAll();
        return juegos.stream().map(juego -> {
            List<Venta> ventas = ventaRepository.findByJuego(juego);
            Long totalVentas = ventas.stream().mapToLong(Venta::getVent_cantidad).sum();
            //Double ingresosTotales = ventas.stream().mapToDouble(venta -> venta.getJuego().getJue_Precio() * venta.getVent_cantidad()).sum();//posibilidad uno 
            Double ingresosTotales = ventas.stream().mapToDouble(venta-> venta.getVent_cantidad() * venta.getVent_precio()).sum();//posibilidad dos

            List<Resena> resenas = resenaRepository.findByJuego(juego);
            Double promedioCalificacion = resenas.stream().mapToDouble(Resena::getRes_calificacion).average().orElse(0.0);
            //Long totalResenas = resenas.stream().count();//opcion 1
            Long totalResenas =(long) resenas.size();//opcion 2
            return CoreDto.builder()
            .juegoId(juego.getJue_id())
            .tituloJuego(juego.getJue_Titulo())
            .precioJuego(juego.getJue_Precio())
            .totalVentas(totalVentas)
            .ingresosTotales(ingresosTotales)
            .promedioCalificacion(promedioCalificacion)
            .totalResenas(totalResenas)
            .build();
        }).collect(Collectors.toList());
    }

}
