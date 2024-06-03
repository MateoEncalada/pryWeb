package com.rungroop.login.models;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "resenas")
public class Resena {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long res_id;

    @ManyToOne
    @JoinColumn(name = "jue_id", nullable = false)
    private Juego juego;

    @ManyToOne
    @JoinColumn(name = "cli_id", nullable = false)
    private Client cliente;
    private Integer res_calificacion;
    private String res_comentario;
}