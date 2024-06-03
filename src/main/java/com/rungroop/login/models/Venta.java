package com.rungroop.login.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "ventas")
public class Venta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long vent_id;

    @ManyToOne
    @JoinColumn(name = "cli_id", nullable = false)
    private Client cliente;

    @ManyToOne
    @JoinColumn(name = "jue_id", nullable = false)
    private Juego juego;

    @Temporal(TemporalType.DATE)
    private Date vent_fecha;
    private Integer vent_cantidad;
    private Double vent_precio;
}