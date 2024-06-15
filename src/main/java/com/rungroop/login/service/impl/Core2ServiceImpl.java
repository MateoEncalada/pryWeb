package com.rungroop.login.service.impl;

import java.util.ArrayList;
import java.util.Date;
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
    public List<Core2Dto> obtenerReporte(Date fechaInicio, Date fechaFin) {
        List<Core2Dto> resultado = new ArrayList<>();

        for (Juego juego : juegoRepository.findAll()) {
            List<Resena> resenasPositivas = new ArrayList<>();
            for (Resena resena : resenaRepository.findByJuego(juego)) {
                if (resena.getRes_calificacion() >= 5 && 
                    !resena.getJue_Fecharesena().before(fechaInicio) && 
                    !resena.getJue_Fecharesena().after(fechaFin)) {
                    resenasPositivas.add(resena);
                }
            }

            if (!resenasPositivas.isEmpty()) {
                Long totalVentas = 0L;
                Double ingresosTotales = 0.0;
                Long totalResenas = (long) resenasPositivas.size();
                Double sumaCalificaciones = 0.0;

                for (Resena resena : resenasPositivas) {
                    sumaCalificaciones += resena.getRes_calificacion();
                }
                Double promedioCalificacion = sumaCalificaciones / totalResenas;

                List<Venta> ventasEnRango = new ArrayList<>();
                for (Venta venta : ventaRepository.findByJuego(juego)) {
                    if (!venta.getVent_fecha().before(fechaInicio) && !venta.getVent_fecha().after(fechaFin)) {
                        ventasEnRango.add(venta);
                    }
                }

                for (Venta venta : ventasEnRango) {
                    totalVentas += venta.getVent_cantidad();
                    ingresosTotales += venta.getVent_cantidad() * venta.getVent_precio();
                }

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
        }

        return resultado.stream()
            .sorted((j1, j2) -> {
                int compareVentas = j2.getTotalVentas().compareTo(j1.getTotalVentas());
                if (compareVentas != 0) {
                    return compareVentas;
                }
                return j2.getPromedioCalificacion().compareTo(j1.getPromedioCalificacion());
            })
            .collect(Collectors.toList());
    }
}

//  package com.rungroop.login.service.impl;

// import java.util.ArrayList;
// import java.util.Date;
// import java.util.List;
// import java.util.stream.Collectors;

// import org.springframework.stereotype.Service;

// import com.rungroop.login.dto.Core2Dto;
// import com.rungroop.login.models.Juego;
// import com.rungroop.login.models.Resena;
// import com.rungroop.login.models.Venta;
// import com.rungroop.login.repository.JuegoRepository;
// import com.rungroop.login.repository.ResenaRepository;
// import com.rungroop.login.repository.VentaRepository;
// import com.rungroop.login.service.Core2Service;

// @Service
// public class Core2ServiceImpl implements Core2Service {
//     private JuegoRepository juegoRepository;
//     private ResenaRepository resenaRepository;
//     private VentaRepository ventaRepository;

//     public Core2ServiceImpl(JuegoRepository juegoRepository, ResenaRepository resenaRepository, VentaRepository ventaRepository) {  
//         this.juegoRepository = juegoRepository;
//         this.resenaRepository = resenaRepository;
//         this.ventaRepository = ventaRepository;
//     } 

//     @Override
//     public List<Core2Dto> obtenerReporte(Date fechaInicio, Date fechaFin) {
//         List<Core2Dto> resultado = new ArrayList<>();

//         for (Juego juego : juegoRepository.findAll()) {
//             List<Resena> resenasPositivas = resenaRepository.findByJuego(juego).stream()
//                 .filter(resena -> resena.getRes_calificacion() >= 5 && !resena.getJue_Fecharesena().before(fechaInicio) && !resena.getJue_Fecharesena().after(fechaFin))
//                 .collect(Collectors.toList());

//             if (!resenasPositivas.isEmpty()) {
//                 Long totalVentas = 0L;
//                 Double ingresosTotales = 0.0;
//                 Long totalResenas = (long) resenasPositivas.size();
//                 Double sumaCalificaciones = resenasPositivas.stream().mapToDouble(Resena::getRes_calificacion).sum();
//                 Double promedioCalificacion = sumaCalificaciones / totalResenas;

//                 List<Venta> ventasEnRango = ventaRepository.findByJuego(juego).stream()
//                     .filter(venta -> !venta.getVent_fecha().before(fechaInicio) && !venta.getVent_fecha().after(fechaFin))
//                     .collect(Collectors.toList());

//                 totalVentas = ventasEnRango.stream().mapToLong(Venta::getVent_cantidad).sum();
//                 ingresosTotales = ventasEnRango.stream().mapToDouble(venta -> venta.getVent_cantidad() * venta.getVent_precio()).sum();

//                 Core2Dto core2Dto = Core2Dto.builder()
//                     .juegoId(juego.getJue_id())
//                     .tituloJuego(juego.getJue_Titulo())
//                     .precioJuego(juego.getJue_Precio())
//                     .totalVentas(totalVentas)
//                     .ingresosTotales(ingresosTotales)
//                     .promedioCalificacion(promedioCalificacion)
//                     .totalResenas(totalResenas)
//                     .build();

//                 resultado.add(core2Dto);
//             }
//         }

//         return resultado.stream()
//             .sorted((j1, j2) -> {
//                 int compareVentas = j2.getTotalVentas().compareTo(j1.getTotalVentas());
//                 if (compareVentas != 0) {
//                     return compareVentas;
//                 }
//                 return j2.getPromedioCalificacion().compareTo(j1.getPromedioCalificacion());
//             })
//             .collect(Collectors.toList());
//     }
// }
