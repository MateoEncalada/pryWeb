package com.rungroop.login.models;

import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "generos")
public class Genero {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long gen_id;

    private String gen_nombre;
    private String gen_descripcion;

    @OneToMany(mappedBy = "genero")
    private List<Juego> juegos;
}
