package com.rungroop.login.models;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "juegos")
public class Juego {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long jue_id;
    private String jue_Titulo;
    private String jue_Descripcion;
    @Temporal(TemporalType.DATE)
    private Date jue_FechaLanzamiento;
    private Double jue_Precio;
    private Integer jue_Inventario;

    @ManyToOne
    @JoinColumn(name = "gen_id", nullable = false)
    private Genero genero;

    @OneToMany(mappedBy = "juego")
    private List<Resena> resenas;
    
    @OneToMany(mappedBy = "juego")
    private List<Venta> ventas;

}
