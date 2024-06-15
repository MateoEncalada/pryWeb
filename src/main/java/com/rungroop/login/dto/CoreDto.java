package com.rungroop.login.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CoreDto {
    private Long juegoId;
    private String tituloJuego;
    private Double precioJuego;
    private Long totalVentas;
    private Double ingresosTotales;
    private Double promedioCalificacion;
    private Long totalResenas;
}