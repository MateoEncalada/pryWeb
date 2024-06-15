package com.rungroop.login.models;

import java.util.Date;


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
@Table(name = "Borrar")

public class Delete {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long jue_id;
    private String jue_Titulo;
    private String jue_Descripcion;
    @Temporal(TemporalType.DATE)
    private Date jue_FechaLanzamiento;
    private Double jue_Precio;
    private Integer jue_Inventario;
    
}
