package com.rungroop.login.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.rungroop.login.dto.Core2Dto;
import com.rungroop.login.models.Juego;
import com.rungroop.login.models.Resena;
import com.rungroop.login.models.Venta;
import com.rungroop.login.repository.JuegoRepository;
import com.rungroop.login.repository.ResenaRepository;
import com.rungroop.login.repository.VentaRepository;
import com.rungroop.login.service.Core2Service;

@Service
public class Core2ServiceImpl implements Core2Service {
    private JuegoRepository juegoRepository;
    private ResenaRepository resenaRepository;
    private VentaRepository ventaRepository;

    public Core2ServiceImpl(JuegoRepository juegoRepository, ResenaRepository resenaRepository, VentaRepository ventaRepository) {  
        this.juegoRepository = juegoRepository;
        this.resenaRepository = resenaRepository;
        this.ventaRepository = ventaRepository;
    } 
    @Override
    public List<Core2Dto> obtenerReporte() {
        List<Core2Dto> resultado= new ArrayList<>();
        for (Juego juego : juegoRepository.findAll()) {
            Long totalVentas = 0L;
            Double ingresosTotales = 0.0;
            Double promedioCalificacion = 0.0;
            Long totalResenas = 0L;
            Double sumaCalificaciones = 0.0;
            //Acumulamos las ventas 
            for (Venta venta : ventaRepository.findByJuego(juego)) {
                totalVentas += venta.getVent_cantidad();
                ingresosTotales += venta.getVent_cantidad() * venta.getVent_precio();
            }
            //Acumulamos las resenas
            /*for (Resena resena : resenaRepository.findByJuego(juego)) { segundo intento con un for*/
                List<Resena> resenas = resenaRepository.findByJuego(juego);
                totalResenas = (long) resenas.size();
                for (Resena resena : resenas) {
                    sumaCalificaciones += resena.getRes_calificacion();
                }
                if (totalResenas > 0) {
                    promedioCalificacion = sumaCalificaciones / totalResenas;
                }
                // promedioCalificacion += resena.getRes_calificacion();
                // totalResenas++;
            
            // promedioCalificacion = promedioCalificacion / totalResenas;
            // resultado.add(new Core2Dto(juego.getJue_id(), juego.getJue_Titulo(), juego.getJue_Precio(), totalVentas, ingresosTotales, promedioCalificacion, totalResenas));
            Core2Dto core2Dto = Core2Dto.builder()
                    .juegoId(juego.getJue_id())
                    .tituloJuego(juego.getJue_Titulo())
                    .precioJuego(juego.getJue_Precio())
                    .totalVentas(totalVentas)
                    .ingresosTotales(ingresosTotales)
                    .promedioCalificacion(promedioCalificacion)
                    .totalResenas(totalResenas)
                    .build();
            resultado.add(core2Dto);
        }
        return resultado.stream() 
            .sorted((j1, j2) -> {
                int compareVentas = j2.getTotalVentas().compareTo(j1.getTotalVentas());
                if (compareVentas != 0) {
                    return compareVentas;
                }
                return j2.getPromedioCalificacion().compareTo(j1.getPromedioCalificacion());
            })
            //.limit(10)
            .collect(Collectors.toList());        
    } 
} 

